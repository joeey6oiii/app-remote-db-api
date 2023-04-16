package serverModules.response.sender;

import serverModules.connection.ConnectionModule;

import java.net.InetAddress;

public interface ResponseAble<T> {
    void sendResponse(ConnectionModule module, InetAddress address, int port, T obj);
}
