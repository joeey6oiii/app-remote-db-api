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

public class DatagramConnectionModule implements ConnectionModule {
    private static final Logger logger = LogManager.getLogger("logger.ConnectionModule");
    private final int BYTE_SIZE = 4096;
    private final DatagramSocket socket;

    protected DatagramConnectionModule(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    @Override
    public RequestData receiveData() {
        byte[] bytes = new byte[BYTE_SIZE];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        try {
            socket.receive(packet);
            logger.debug("Received data");

            return new RequestData(packet.getData(), new CallerBack(packet.getAddress(), packet.getPort()));
        } catch (IOException e) {
            logger.error("Something went wrong during receiving data: " + e.getMessage());
        }
        return null; // fix
    }

    @Override
    public void sendData(byte[] data, InetAddress address, int port) {
        try {
            socket.send(new DatagramPacket(data, data.length, address, port));
            logger.debug("Data sent");
        } catch (IOException e) {
            logger.error("Something went wrong during data sending: " + e.getMessage());
        }
    }
}