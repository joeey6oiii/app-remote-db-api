package clientModules.request.sender;

import clientModules.connection.ConnectionModule;
import clientModules.response.reader.ResponseReader;
import requests.Request;
import responses.Response;
import serializer.ObjectSerializer;

import java.io.IOException;

public class RequestSender implements RequestAble<Response, Request> {

    @Override
    public Response sendRequest(ConnectionModule module, Request request) {
        ObjectSerializer os = new ObjectSerializer();
        try {
            module.sendData(os.serialize(request));

//            return new ResponseReader().readResponse(module.receiveData());
            return new ResponseReader().readResponse(module.nonBlockingReceiveData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
