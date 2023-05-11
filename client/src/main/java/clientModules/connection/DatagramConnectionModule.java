package clientModules.connection;

import exceptions.ServerUnavailableException;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * A class that represents the data transfer datagram connection module.
 */

public class DatagramConnectionModule implements DataTransferConnectionModule {
    private final int PACKET_SIZE = 1024;
    private DatagramChannel datagramChannel;
    private final SocketAddress socketAddress;

    /**
     * A constructor for the data transfer datagram connection module.
     *
     * @param datagramChannel the specified datagram channel
     * @param socketAddress the specified server address
     */

    protected DatagramConnectionModule(DatagramChannel datagramChannel, SocketAddress socketAddress) {
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
    }

    /**
     * A method that returns the datagram channel.
     */

    protected DatagramChannel getDatagramChannel() {
        return this.datagramChannel;
    }

    /**
     * A method that connects the {@link DatagramConnectionModule} to the server.
     *
     * @throws IOException if failed during I/O operations
     */

    @Override
    public void connect() throws IOException {
        if (!datagramChannel.isConnected() && datagramChannel.isOpen()) {
            datagramChannel.connect(socketAddress);
        }
    }

    /**
     * A method that disconnects the {@link DatagramConnectionModule} from the server.
     *
     * @throws IOException if failed during I/O operations
     */

    @Override
    public void disconnect() throws IOException {
        if (datagramChannel.isConnected() && datagramChannel.isOpen()) {
            try {
                datagramChannel.disconnect();
                datagramChannel.close();
            } catch (IOException e) {
                datagramChannel.close();
            }
        }
    }

    /**
     * A method that sends the specified data.
     *
     * @param data data to send
     * @throws IOException if failed during I/O operations
     * @throws ServerUnavailableException if the server was unavailable during sending and receiving operations
     */

    @Override
    public void sendData(byte[] data) throws IOException, ServerUnavailableException {
        ByteBuffer buffer = ByteBuffer.allocate(data.length);
        buffer.put(data);
        buffer.flip();

        try {
            datagramChannel.send(buffer, socketAddress);
        } catch (PortUnreachableException e) {
            throw new ServerUnavailableException("Server is currently unavailable");
        }
    }

    /**
     * A method that receives data. Supports blocking and non-blocking datagram channel.
     *
     * @throws IOException if failed during I/O operations
     * @throws ServerUnavailableException if the server was unavailable during sending and receiving operations
     */

    @Override
    public byte[] receiveData() throws IOException, ServerUnavailableException {
        ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);
        byte[] data;

        if (datagramChannel.isBlocking()) {
            this.datagramChannel.receive(buffer);
        } else {
            boolean read = false;
            Selector selector = Selector.open();
            datagramChannel.register(selector, SelectionKey.OP_READ);

            while (!read) {
                int readyChannels;
                readyChannels = selector.select();
                if (readyChannels == 0) {
                    continue;
                }

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    if (key.isReadable()) {
                        datagramChannel = (DatagramChannel) key.channel();
                        try {
                            datagramChannel.read(buffer);
                            read = true;
                        } catch (PortUnreachableException e) {
                            throw new ServerUnavailableException("Server is currently unavailable");
                        }
                    }

                    keyIterator.remove();
                }
            }
        }

        buffer.flip();
        data = new byte[buffer.remaining()];
        buffer.get(data);

        return data;
    }
}