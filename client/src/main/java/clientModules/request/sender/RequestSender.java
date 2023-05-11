package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.response.reader.ResponseReader;
import exceptions.ServerUnavailableException;
import requests.Request;
import responses.Response;
import serializer.ObjectSerializer;

import java.io.IOException;

/**
 * A class that represents the base request sender.
 */

public class RequestSender implements RequestAble<Response, Request> {

    /**
     * A method that serializes the request and sends it to the server. After, gets and returns the response.
     *
     * @param module server core
     * @param request request
     * @return base response
     */

    @Override
    public Response sendRequest(DataTransferConnectionModule module, Request request) throws IOException, ServerUnavailableException {
        Response response = null;
        ObjectSerializer os = new ObjectSerializer();
        try {
            module.sendData(os.serialize(request));
            byte[] data = module.receiveData();

            if (data != null) {
                response = new ResponseReader().readResponse(data);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Unexpected error: Could not find response class");
        }

        return response;
    }

}
