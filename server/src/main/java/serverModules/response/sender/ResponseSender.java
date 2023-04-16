package serverModules.response.sender;

import responses.Response;
import serverModules.connection.ConnectionModule;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class ResponseSender implements ResponseAble<Response> {

    @Override
    public void sendResponse(ConnectionModule module, InetAddress address, int port, Response response) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(response);
            oos.flush();
            byte[] data = bos.toByteArray();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            module.getSocket().send(packet);
            oos.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
