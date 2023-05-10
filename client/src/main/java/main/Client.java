package main;

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
        DatagramConnectionModule module = null;
        try {
            module = factory.createConfigureBlocking(new InetSocketAddress(InetAddress.getLocalHost(), PORT), false);

            module.connect(); // во прикол, PortUnreachableException выбрасывается не при подключении к серверу (см стр 38)
            System.out.println("Server connection established\nReceiving commands..."); // (!) потом разберемся с PrintStream (!)

            new CommandsReceiver().receiveCommands(module); // вот тут выбрасывается PUE при невозможности отправить данные
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