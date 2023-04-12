package serverModules.request;

import serverModules.request.requests.Request;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;

public class RequestReader implements RequestReadAble<Request> {

    @Override
    public Request readRequest(DatagramPacket packet) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (Request) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}