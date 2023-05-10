package serverModules.request.reader;

import requests.Request;
import serializer.ObjectSerializer;

import java.io.IOException;

public class RequestReader implements RequestReadAble<Request> {

    @Override
    public Request readRequest(byte[] data) throws IOException, ClassNotFoundException {
        ObjectSerializer os = new ObjectSerializer();
        return (Request) os.deserialize(data);
    }
}