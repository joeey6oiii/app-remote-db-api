package serverModules.request.reader;

import requests.Request;
import serializer.ObjectSerializer;

public class RequestReader implements RequestReadAble<Request> {

    @Override
    public Request readRequest(byte[] data) {
        ObjectSerializer os = new ObjectSerializer();
        try {
            return (Request) os.deserialize(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}