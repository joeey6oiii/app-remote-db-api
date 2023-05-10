package clientModules.request.sender;

import clientModules.connection.DatagramConnectionModule;
import clientModules.response.reader.ResponseReader;
import requests.Request;
import responses.Response;
import serializer.ObjectSerializer;

import java.io.IOException;
import java.net.PortUnreachableException;

public class RequestSender implements RequestAble<Response, Request> {

    @Override
    public Response sendRequest(DatagramConnectionModule module, Request request) {
        ObjectSerializer os = new ObjectSerializer();
        try {
            module.sendData(os.serialize(request));

            return new ResponseReader().readResponse(module.receiveData());
        } catch (PortUnreachableException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            // fix
        }
        return null; // fix
    }
}
