package clientModules.connection;

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
        ReceiveDataAble<byte[]>, NonBlockingReceiveDataAble<byte[]> {
    private static final int MAX_PACKET_SIZE = (int) (Math.pow(2, 16) - 1);
    private final int BUFFER_SIZE = 4096;
    private final DatagramChannel datagramChannel;
    private final SocketAddress socketAddress;

    public ConnectionModule(DatagramChannel datagramChannel, SocketAddress socketAddress) {
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
    }

    public DatagramChannel getDatagramChannel() {
        return this.datagramChannel;
    }

    public SocketAddress getAddress() {
        return this.socketAddress;
    }

    @Override
    public void connect() {
        if (!datagramChannel.isConnected() && datagramChannel.isOpen()) {
            try {
                datagramChannel.connect(socketAddress);
            } catch (IOException e) {
                e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sendData(byte[] data) {
        ByteBuffer buffer = ByteBuffer.allocate(data.length);
        buffer.put(data);
        buffer.flip();
        try {
            datagramChannel.send(buffer, socketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Пока идет тестирование неблокирующего receive этот метод будет оставаться тут. Если неблокирующий receive
    // успешно пройдет все тесты, то этот метод будет удален из кода, а канал будет по умолчанию non blocking
    @Override
    public byte[] receiveData() {
        boolean isBlocking = datagramChannel.isBlocking();
        boolean set = false;
        if (!isBlocking) {
            try {
                datagramChannel.configureBlocking(true);
                set = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try {
            this.datagramChannel.receive(buffer);
            if (set) {
                datagramChannel.configureBlocking(isBlocking);
            }
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // В данный момент тестируется. Возможна замена блокирующего метода на неблокирующий
    @Override
    public byte[] nonBlockingReceiveData() {
        ByteBuffer buffer = ByteBuffer.allocate(MAX_PACKET_SIZE);
        boolean isBlocking = datagramChannel.isBlocking();
        boolean set = false;
        if (isBlocking) {
            try {
                datagramChannel.configureBlocking(false);
                set = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
                readyChannels = selector.select(); // .selectNow() ?
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

                if (set) {
                    try {
                        datagramChannel.configureBlocking(isBlocking);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                keyIterator.remove();
            }
        }
    }
}