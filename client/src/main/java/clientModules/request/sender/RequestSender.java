package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.response.reader.ResponseReader;
import requests.Request;
import responses.Response;
import serializer.ObjectSerializer;

import java.io.IOException;
import java.net.PortUnreachableException;

public class RequestSender implements RequestAble<Response, Request> {

    @Override
    public Response sendRequest(DataTransferConnectionModule module, Request request) {
        Response response = null;
        ObjectSerializer os = new ObjectSerializer();
        try {
            module.sendData(os.serialize(request));
            byte[] data = module.receiveData();

            if (data != null) {
                response = new ResponseReader().readResponse(data);
            }
        } catch (PortUnreachableException e) {
            System.out.println("Failed to send request due to server unavailability"); // сюды закидыш команды в лист неотпр
        } catch (ClassNotFoundException e) {
            System.out.println("Unexpected error: Could not find response class");
        } catch (IOException ioe) {
            System.out.println("Something went wrong during I/O operations");
        }
        return response;
    }
}
