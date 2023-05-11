package serverModules.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serverModules.callerBack.CallerBack;
import serverModules.request.data.RequestData;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * A class that represents the datagram connection module.
 */

public class DatagramConnectionModule implements ConnectionModule {
    private static final Logger logger = LogManager.getLogger("logger.ConnectionModule");
    private final int BYTE_SIZE = 4096;
    private final DatagramSocket socket;

    /**
     * A constructor for the datagram connection module.
     *
     * @param port the specified server port
     * @throws SocketException if error happened during socket operations
     */

    protected DatagramConnectionModule(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    /**
     * A method that receives data.
     *
     * @return a class that contains the information about the received request.
     * Returns empty {@link RequestData} if empty request received.
     */

    @Override
    public RequestData receiveData() {
        byte[] bytes = new byte[BYTE_SIZE];
        try {
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            socket.receive(packet);
            logger.debug("Received data");

            return new RequestData(packet.getData(), new CallerBack(packet.getAddress(), packet.getPort()));
        } catch (IOException e) {
            logger.error("Something went wrong during receiving data", e);
        }
        return new RequestData();
    }

    /**
     * A method that sends the specified data to the specified address and port.
     *
     * @param data data to send
     * @param address address to send to
     * @param port port to send to
     */

    @Override
    public void sendData(byte[] data, InetAddress address, int port) {
        try {
            socket.send(new DatagramPacket(data, data.length, address, port));
            logger.debug("Data sent");
        } catch (IOException e) {
            logger.error("Something went wrong during data sending", e);
        }
    }

}