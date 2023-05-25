package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.response.reader.ResponseReader;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;
import utility.UdpDataTransferUtilities;
import utils.HeaderParser;
import utils.ResponseAssembler;
import utils.ResponseDataParser;
import requests.Request;
import response.data.FragmentHeader;
import response.responses.Response;
import serializer.ObjectSerializer;

import java.io.IOException;
import java.util.HashMap;

/**
 * A class that represents the base request sender.
 */

public class RequestSender implements RequestAble<Response, Request> {

    /**
     * A method that serializes the request and sends it to the server. After, receives chunks, collects into one
     * serialized response, deserializes and returns the read response.
     *
     * @param module server core
     * @param request request
     * @return base response
     */

    @Override
    public Response sendRequest(DataTransferConnectionModule module, Request request) throws IOException, ServerUnavailableException, ResponseTimeoutException {
        Response response = null;
        HashMap<Integer, byte[]> chunks = new HashMap<>();
        ObjectSerializer os = new ObjectSerializer();

        HeaderParser headerParser = new HeaderParser();
        ResponseDataParser responseDataParser = new ResponseDataParser();
        FragmentHeader header;

        try {
            module.sendData(os.serialize(request));

            do {
                byte[] data = module.receiveData();
                header = headerParser.parseHeader(data);
                byte[] partOfResponseData = responseDataParser.extractResponseData(data);

                chunks.put(header.getPacketIndex(), partOfResponseData);

            } while (header.getPacketIndex() != -1);

            byte[] responseData = new ResponseAssembler().combineResponseParts(chunks);
            response = new ResponseReader().readResponse(responseData);
        } catch (IllegalArgumentException e) {
            System.out.println("Response part is missing");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find response class");
        }

        return response;
    }

}
