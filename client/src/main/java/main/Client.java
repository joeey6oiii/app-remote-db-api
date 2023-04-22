package main;

import clientModules.connection.ConnectionModule;
import clientModules.connection.ConnectionModuleConfigurator;
import clientModules.request.sender.RequestSender;
import clientModules.response.manager.ResponseHandlerManager;
import commands.CommandDescriptionsContainer;
import requests.CommandDescriptionsRequest;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Program entry point class. Contains <code>main()</code> method.
 */

public class Client {

    private final static int PORT = 9999;

    /**
     * Program entry point.
     *
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        ResponseHandlerManager manager = new ResponseHandlerManager();

        try {
            ConnectionModule module = new ConnectionModuleConfigurator()
                    .newInstanceConfigureBlocking(new InetSocketAddress(InetAddress.getLocalHost(), PORT), false);
            module.connect();

            manager.manageResponse(new RequestSender().sendRequest(module, new CommandDescriptionsRequest()));

            module.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}