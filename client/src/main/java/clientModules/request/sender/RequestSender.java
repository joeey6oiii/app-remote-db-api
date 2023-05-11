package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.response.reader.ResponseReader;
import exceptions.ServerUnavailableException;
import requests.Request;
import responses.Response;
import serializer.ObjectSerializer;

import java.io.IOException;

public class RequestSender implements RequestAble<Response, Request> {

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
