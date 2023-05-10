package clientModules.connection;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class DatagramConnectionModule implements ConnectionModule, SendDataAble, ReceiveDataAble<byte[]> {
    private final int PACKET_SIZE = 4096;
    private DatagramChannel datagramChannel;
    private final SocketAddress socketAddress;

    protected DatagramConnectionModule(DatagramChannel datagramChannel, SocketAddress socketAddress) {
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
    }

    protected DatagramChannel getDatagramChannel() {
        return this.datagramChannel;
    }

    @Override
    public void connect() {
        if (!datagramChannel.isConnected() && datagramChannel.isOpen()) {
            try {
                datagramChannel.connect(socketAddress);
            } catch (IOException e) {

            }
        }
    }

    @Override
    public void disconnect() {
        if (datagramChannel.isConnected() && datagramChannel.isOpen()) {
            try {
                datagramChannel.disconnect();
                datagramChannel.close();
            } catch (IOException e) {

            }
        }
    }

    @Override
    public void sendData(byte[] data) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(data.length);
        buffer.put(data);
        buffer.flip();

        datagramChannel.send(buffer, socketAddress);
    }

    @Override
    public byte[] receiveData() throws IOException {
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
                        datagramChannel.read(buffer);
                        read = true;
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