package serverModules.connection;

import java.net.InetAddress;

public interface SendDataAble {
    void sendData(byte[] data, InetAddress address, int port);
}
