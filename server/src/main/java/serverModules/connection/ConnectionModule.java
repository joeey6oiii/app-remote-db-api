package serverModules.connection;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ConnectionModule implements ReceiveConnectionAble {
    private final DatagramSocket socket;

    public ConnectionModule(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    public DatagramSocket getSocket() {
        return socket;
    }
}