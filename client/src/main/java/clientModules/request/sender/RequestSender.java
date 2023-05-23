package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.response.reader.ResponseReader;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;
import requests.Request;
import responses.Response;
import serializer.ObjectSerializer;

import java.io.IOException;
import java.util.HashMap;

/**
 * A class that represents the base request sender.
 */

public class RequestSender implements RequestAble<HashMap<Integer, Response>, Request> {

    /**
     * A method that serializes the request and sends it to the server. After, gets and returns the response.
     *
     * @param module server core
     * @param request request
     * @return base response
     */

    @Override
    public HashMap<Integer, Response> sendRequest(DataTransferConnectionModule module, Request request) throws IOException, ServerUnavailableException, ResponseTimeoutException {
        Response response;
        HashMap<Integer, Response> responses = new HashMap<>();
        ObjectSerializer os = new ObjectSerializer();
        try {
            module.sendData(os.serialize(request));
            byte[] data = module.receiveData();
            response = new ResponseReader().readResponse(data);
            responses.put(response.getCurrentResponseNumber(), response);
            int totalResponsesAmount = response.getTotalResponsesAmount();
            int i = 1;

            while (i <= totalResponsesAmount - 1) {
                i++;
                data = module.receiveData();
                response = new ResponseReader().readResponse(data);
                responses.put(response.getCurrentResponseNumber(), response);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Unexpected error: Could not find response class");
        }

        return responses;
    }

}
