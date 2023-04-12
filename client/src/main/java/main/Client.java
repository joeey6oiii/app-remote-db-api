package main;

import clientModules.initializer.ChannelInitializer;
import clientModules.connection.ConnectionModule;

import java.io.*;
import java.net.*;

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
            ConnectionModule connectionModule = new ConnectionModule(new ChannelInitializer().init(),
                    new InetSocketAddress(InetAddress.getLocalHost(), PORT));
            connectionModule.connect();


            connectionModule.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}