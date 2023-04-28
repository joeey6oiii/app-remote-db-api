package main;

import clientModules.connection.ConnectionModule;
import clientModules.connection.ConnectionModuleConfigurator;
import clientModules.response.receivers.CommandsReceiver;
import commandsModule.commands.ClientCommandsKeeper;
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

        PrintWriter out = new PrintWriter(System.out);

        try {
            ConnectionModule module = new ConnectionModuleConfigurator()
                    .newInstanceConfigureBlocking(new InetSocketAddress(InetAddress.getLocalHost(), PORT), true);

            module.connect();
            out.println("Server connection established\nReceiving commands...");
            out.flush();

            new CommandsReceiver().receiveCommandDescriptions(module);
            out.println("Received commands");
            out.flush();

            CommandHandler handler = new CommandHandler(ClientCommandsKeeper.getCommands(), new Scanner(System.in));

            System.out.println("Console input allowed");
            out.flush();
            handler.start(module);

            module.disconnect();
        } catch (IOException e) {
            out.println("Unpredicted error " + e.getMessage());
            out.flush();
        } finally {
            out.close();
        }
    }

}