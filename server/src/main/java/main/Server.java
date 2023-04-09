package main;

import serverModules.connection.ConnectionModule;
import serverModules.processors.ObjectProcessor;
import serverModules.request.RequestReader;
import serverModules.response.ResponseSender;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    private final static int PORT = 9999;
    private final static int BUFFER_SIZE = 4096; // тут этого быть не должно, пока для примера есть, потом раскидаем по классам

    public static void main(String[] args) throws Exception {
        ConnectionModule module = new ConnectionModule(PORT);
        DatagramSocket socket = module.getSocket();
        byte[] buffer = new byte[BUFFER_SIZE];

        ObjectProcessor processor = new ObjectProcessor();
        RequestReader requestReader = new RequestReader();
        ResponseSender sender = new ResponseSender();

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, BUFFER_SIZE);
            socket.receive(packet);
            Object obj = requestReader.readRequest(packet);
            Object result = processor.process(obj);
            sender.sendResponse(socket, result, packet.getAddress().getHostAddress(), packet.getPort());
        }
    }
}
