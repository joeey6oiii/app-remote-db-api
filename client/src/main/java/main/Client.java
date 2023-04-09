package main;

import ClientModules.serverConnection.ChannelConfigurator;
import ClientModules.serverConnection.ConnectionModule;

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
            ConnectionModule connectionModule = new ConnectionModule(new ChannelConfigurator().init(),
                    new InetSocketAddress(InetAddress.getLocalHost(), PORT));
            connectionModule.connect();



            connectionModule.disconnect();
        } catch (UnknownHostException ignored) {} catch (IOException e) {}
    }
}