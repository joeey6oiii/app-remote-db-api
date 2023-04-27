package main;

import clientModules.connection.ConnectionModule;
import clientModules.connection.ConnectionModuleConfigurator;
import clientModules.response.receivers.CommandDescriptionsReceiver;
import commandsModule.commands.CommandDescriptionsKeeper;
import commandsModule.handler.CommandHandler;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Program entry point class. Contains <code>main()</code> method.
 */

public class Client {

    private final static int PORT = 64999;

    /**
     * Program entry point.
     *
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        try {
            ConnectionModule module = new ConnectionModuleConfigurator()
                    .newInstanceConfigureBlocking(new InetSocketAddress(InetAddress.getLocalHost(), PORT), true);
            module.connect();

            new CommandDescriptionsReceiver().receiveCommandDescriptions(module);

            CommandHandler handler = new CommandHandler(CommandDescriptionsKeeper.getCommandDescriptions(), new Scanner(System.in));

            System.out.println("Console input allowed");
            handler.start(module);

            module.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}