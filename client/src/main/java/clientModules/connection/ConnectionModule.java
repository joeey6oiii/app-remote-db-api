package clientModules.connection;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ConnectionModule implements ConnectionManageAble, SendDataAble, ReceiveDataAble<byte[]> {
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
                // todo: реализовать логирование тута
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
                // todo: реализовать логирование здесь
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

    @Override
    public byte[] receiveData() {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try {
            this.datagramChannel.receive(buffer);
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}