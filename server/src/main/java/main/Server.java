package main;

import responses.CommandDescriptionsResponse;
import serverModules.connection.ConnectionModule;
import serverModules.request.reader.RequestReader;
import serverModules.response.sender.ResponseSender;

import java.util.ArrayList;

public class Server {
    private final static int PORT = 9999;

    public static void main(String[] args) {
        try {
            ConnectionModule module = new ConnectionModule(PORT);

            while (true) {
                System.out.println(new RequestReader().readRequest(module.receiveData()));
                new ResponseSender().sendResponse(module, module.getCallerBack().getAddress(), module.getCallerBack().getPort(),
                        new CommandDescriptionsResponse(new ArrayList<>()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
