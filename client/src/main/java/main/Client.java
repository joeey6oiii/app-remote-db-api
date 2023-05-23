package main;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.connection.DatagramConnectionModuleFactory;
import clientModules.response.receivers.CommandsReceiver;
import commandsModule.ClientCommandsKeeper;
import commandsModule.handler.CommandHandler;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
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
        try {
            DataTransferConnectionModule module = factory.createConfigureBlocking
                    (new InetSocketAddress(InetAddress.getLocalHost(), PORT), false);

            module.connect();
            System.out.println("Server connection established");

            int timeout = 4999;
            boolean receivedCommands = false;
            while (!receivedCommands) {
                System.out.println("Trying to receive commands...");
                try {
                    new CommandsReceiver().receiveCommands(module);
                    receivedCommands = true;
                } catch (ServerUnavailableException | ResponseTimeoutException | IOException e) {
                    Thread.sleep(timeout);
                }
            }
            System.out.println("Received commands");

            CommandHandler handler = new CommandHandler(ClientCommandsKeeper.getCommands(), new Scanner(System.in), module);

            System.out.println("Console input allowed");
            handler.startHandling();

        } catch (UnknownHostException e) {
            System.out.println("Could not find host");
        } catch (IOException e) {
            System.out.println("Something went wrong during I/O operations");
        } catch (Exception e) {
            System.out.println("Unexpected error happened during client operations");
        }
    }

}