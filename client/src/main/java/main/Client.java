package main;

import clientModules.connection.ConnectionModule;
import clientModules.connection.ConnectionModuleConfigurator;
import clientModules.request.sender.RequestSender;
import clientModules.response.receivers.CommandDescriptionsReceiver;
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

        try {
            ConnectionModule module = new ConnectionModuleConfigurator()
                    .newInstanceConfigureBlocking(new InetSocketAddress(InetAddress.getLocalHost(), PORT), false);
            module.connect();

            new CommandDescriptionsReceiver().handleResponseContent(new RequestSender().sendRequest(module, new CommandDescriptionsRequest()));
            System.out.println(CommandDescriptionsContainer.getCommandDescriptions());

            module.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}