package clientModules.connection;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class ConnectionModule implements ConnectionManageAble {
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
                // todo
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
                // todo
            }
        }
    }
}