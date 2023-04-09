package serverModules.response;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ResponseSender implements ResponseAble<Object> {

    @Override
    public void sendResponse(DatagramSocket socket, Object obj, String address, int port) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
            oos.flush();
            byte[] data = bos.toByteArray();
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(address), port);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
