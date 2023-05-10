package main;

import clientModules.connection.ConnectionModule;
import clientModules.connection.DataTransferConnectionModule;
import clientModules.connection.DatagramConnectionModule;
import clientModules.connection.DatagramConnectionModuleFactory;
import clientModules.response.receivers.CommandsReceiver;
import commandsModule.ClientCommandsKeeper;
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

        DatagramConnectionModuleFactory factory = new DatagramConnectionModuleFactory();
        DataTransferConnectionModule module = null;
        try {
            module = factory.createConfigureBlocking(new InetSocketAddress(InetAddress.getLocalHost(), PORT), false);

            module.connect();
            System.out.println("Server connection established\nTrying to receive commands...");

            new CommandsReceiver().receiveCommands(module);
            System.out.println("Received commands");

            CommandHandler handler = new CommandHandler(ClientCommandsKeeper.getCommands(), new Scanner(System.in), module);

            System.out.println("Console input allowed");
            handler.start();

        } catch (IOException e) {
            System.out.println("Unpredicted error " + e.getMessage());
        } finally {
            module.disconnect();
        }
    }

}