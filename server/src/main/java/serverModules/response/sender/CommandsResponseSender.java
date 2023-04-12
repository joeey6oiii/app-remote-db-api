package serverModules.response.sender;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CommandsResponseSender implements ResponseAble<CommandsResponseSender> {

    @Override
    public void sendResponse(DatagramSocket socket, String address, int port, CommandsResponseSender commands) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(commands);
            oos.flush();
            byte[] data = bos.toByteArray();
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(address), port);
            socket.send(packet);
        } catch (Exception e) {

        }
    }
}
