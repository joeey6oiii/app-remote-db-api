package serverModules.connection;

import serverModules.request.data.RequestData;

import java.net.InetAddress;

public interface ConnectionModule {
    RequestData receiveData();
    void sendData(byte[] data, InetAddress address, int port);
}
