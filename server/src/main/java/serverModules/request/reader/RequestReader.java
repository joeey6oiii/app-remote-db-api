package serverModules.request.reader;

import requests.Request;
import serializer.ObjectSerializer;

import java.net.DatagramPacket;

public class RequestReader implements RequestReadAble<Request> {

    @Override
    public Request readRequest(DatagramPacket packet) {
        ObjectSerializer os = new ObjectSerializer();
        try {
            return (Request) os.deserialize(packet.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}