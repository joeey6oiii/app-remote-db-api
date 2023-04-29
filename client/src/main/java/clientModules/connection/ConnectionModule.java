package clientModules.connection;

import exceptions.LockStateException;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class ConnectionModule implements ConnectionManageAble, SendDataAble,
        BlockingReceiveDataAble<byte[]>, NonBlockingReceiveDataAble<byte[]> {
    private final int PACKET_SIZE = 4096;
    private final int BUFFER_SIZE = 4096;
    private final DatagramChannel datagramChannel;
    private final SocketAddress socketAddress;

    protected ConnectionModule(DatagramChannel datagramChannel, SocketAddress socketAddress) {
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
    }

    protected DatagramChannel getDatagramChannel() {
        return this.datagramChannel;
    }

    protected SocketAddress getAddress() {
        return this.socketAddress;
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

    public boolean isBlocking() {
        return this.datagramChannel.isBlocking();
    }

    @Override
    public void sendData(byte[] data) {
        ByteBuffer buffer = ByteBuffer.allocate(data.length);
        buffer.put(data);
        buffer.flip();
        try {
            datagramChannel.send(buffer, socketAddress);
        } catch (IOException e) {

        }
    }

    @Override
    public byte[] blockingReceiveData() throws LockStateException {
        if (!datagramChannel.isBlocking()) {
            throw new LockStateException("DatagramChannel is non-blocking");
        }

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        byte[] data = new byte[0];
        try {
            this.datagramChannel.receive(buffer);
            buffer.flip();
            data = new byte[buffer.remaining()];
            buffer.get(data);
        } catch (IOException e) {

        }
        return data;
    }

    @Override
    public byte[] nonBlockingReceiveData() throws LockStateException {
        if (datagramChannel.isBlocking()) {
            throw new LockStateException("DatagramChannel is blocking");
        }

        ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);

        Selector selector = null;
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            datagramChannel.register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }

        while (true) {
            int readyChannels = 0;
            try {
                assert selector != null;
                readyChannels = selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (readyChannels == 0) {
                continue;
            }

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                if (key.isReadable()) {
                    DatagramChannel datagramChannel = (DatagramChannel) key.channel();
                    try {
                        datagramChannel.read(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    buffer.flip();
                    byte[] data = new byte[buffer.remaining()];
                    buffer.get(data);
                    try {
                        datagramChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return data;
                }

                keyIterator.remove();
            }
        }
    }
}