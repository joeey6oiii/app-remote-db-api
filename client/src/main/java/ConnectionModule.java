import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class ConnectionModule implements ConnectAble {
    DatagramChannel datagramChannel;
    SocketAddress socketAddress;

    public ConnectionModule(DatagramChannel datagramChannel, SocketAddress socketAddress) {
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
    }

    public void connect() {
        if (!datagramChannel.isConnected() && datagramChannel.isOpen()) {
            try {
                datagramChannel.connect(socketAddress);
            } catch (IOException e) {

            }
        }
    }

    public void disconnect() {
        if (datagramChannel.isConnected() && datagramChannel.isOpen()) {
            try {
                datagramChannel.disconnect();
                datagramChannel.close();
            } catch (IOException e) {

            }
        }
    }


}
