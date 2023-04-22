package serverModules.connection;

import requests.Request;
import serverModules.callerBack.CallerBack;
import serverModules.request.data.RequestData;
import serverModules.request.reader.RequestReader;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ConnectionModule implements ReceiveDataAble<RequestData>, SendDataAble {
    private final int BYTE_SIZE = 1024;
    private final DatagramSocket socket;

    protected ConnectionModule(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    @Override
    public RequestData receiveData() {
        byte[] bytes = new byte[BYTE_SIZE];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        try {
            socket.receive(packet);

            CallerBack callerBack = new CallerBack(packet.getAddress(), packet.getPort()); // RequestData && RequestDataInitializer ?. status 0 or 1, if 0 -> continue ?
            Request request = new RequestReader().readRequest(packet.getData());
            return new RequestData(callerBack, request);
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