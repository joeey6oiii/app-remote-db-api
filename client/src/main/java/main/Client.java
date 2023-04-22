package main;

import clientModules.connection.ConnectionModule;
import clientModules.connection.ConnectionModuleConfigurator;

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
                    .initConfigureBlocking(new InetSocketAddress(InetAddress.getLocalHost(), PORT), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}