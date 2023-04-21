package serverModules.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ConnectionModule implements ReceiveDataAble<byte[]>, SendDataAble {
    private final int BYTE_SIZE = 1024;
    private final DatagramSocket socket;

    public ConnectionModule(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    @Override
    public byte[] receiveData() {
        byte[] bytes = new byte[BYTE_SIZE];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        try {
            socket.receive(packet);
            // сюда надо калербэка воткнуть, но не прямо в конекшн модуль, нужен отдельный класс👍
            return packet.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendData(byte[] data, InetAddress address, int port) {
        try {
            socket.send(new DatagramPacket(data, data.length, address, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}