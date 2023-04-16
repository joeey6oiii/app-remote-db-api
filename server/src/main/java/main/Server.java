package main;

import responses.CommandDescriptionsResponse;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;
import serverModules.request.reader.RequestReader;
import serverModules.response.sender.ResponseSender;

import java.net.DatagramPacket;
import java.util.ArrayList;

public class Server {
    private final static int PORT = 9999;

    public static void main(String[] args) {
        try {
            ConnectionModule module = new ConnectionModule(PORT);

            while (true) {
                byte[] bytes = new byte[1024];
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                module.getSocket().receive(packet);
                System.out.println(new RequestReader().readRequest(packet)); // test
                CallerBack callerBack = new CallerBack(packet.getAddress(), packet.getPort());
                new ResponseSender().sendResponse(module, callerBack.getAddress(), callerBack.getPort(),
                        new CommandDescriptionsResponse(new ArrayList<>()));
            }
        } catch (Exception e) {

        }
    }
}
