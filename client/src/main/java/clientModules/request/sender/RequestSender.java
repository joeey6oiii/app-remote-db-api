package clientModules.request.sender;

import clientModules.connection.ConnectionModule;
import clientModules.response.reader.ResponseReader;
import requests.Request;
import responses.Response;

import java.io.*;
import java.nio.ByteBuffer;

public class RequestSender implements RequestAble<Response, Request> {
    private final int BUFFER_SIZE = 4096;

    @Override
    public Response sendRequest(ConnectionModule module, Request request) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        byte[] dataToSend;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(request); // Возможно имеет смысл вынести все составляющие writeObject() в отдельный класс

            dataToSend = baos.toByteArray();
            module.sendData(dataToSend);

            oos.close();
            baos.close();

            module.getDatagramChannel().receive(buffer); // Имеет смысл под ресив сделать отдельный метод. Просто ООП +
                                                         // удобнее будет работать с неблокирующим каналом.\
            return new ResponseReader().readResponse(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
