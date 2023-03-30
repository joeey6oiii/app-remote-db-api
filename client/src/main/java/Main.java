import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Objects;
import java.util.Scanner;

/**
 * Client entry point class. Contains <code>main()</code> method.
 */

public class Main {

    /**
     * Program entry point.
     *
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        int port = 9999;

        try {
            DatagramChannel datagramChannel = new ChannelConfigurator().init();
            datagramChannel.configureBlocking(false);
            ConnectionModule connectionModule = new ConnectionModule(datagramChannel,
                    new InetSocketAddress(InetAddress.getLocalHost(), port));
            connectionModule.connect();

            // code

            connectionModule.disconnect();
        } catch (UnknownHostException ignored) {} catch (IOException e) {} // не трогать (даже если очень чешутся руки нажать collapse)
    }
}