package serverModules.request.reader;

import requests.Request;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;

public class RequestReader implements RequestReadAble<Request> {

    @Override
    public Request readRequest(DatagramPacket packet) {
        ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(bis);
            Request request = (Request) ois.readObject();
            ois.close();
            bis.close();
            return request;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}