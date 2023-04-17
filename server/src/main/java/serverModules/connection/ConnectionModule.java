package serverModules.connection;

import serverModules.callerBack.CallerBack;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ConnectionModule implements ReceiveConnectionAble, ReceiveDataAble<byte[]> {
    private final int BYTE_SIZE = 1024;
    private final DatagramSocket socket;
    private CallerBack callerBack;

    public ConnectionModule(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public CallerBack getCallerBack() {
        return this.callerBack;
    }

    @Override
    public byte[] receiveData() {
        byte[] bytes = new byte[BYTE_SIZE];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        try {
            socket.receive(packet);
            callerBack = new CallerBack(packet.getAddress(), packet.getPort());
            return packet.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}