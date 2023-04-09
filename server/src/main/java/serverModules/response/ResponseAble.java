package serverModules.response;

import java.net.DatagramSocket;

public interface ResponseAble<T> {
    void sendResponse(DatagramSocket socket, T obj, String address, int port);
}
