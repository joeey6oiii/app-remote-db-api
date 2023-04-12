package serverModules.response.sender;

import java.net.DatagramSocket;

public interface ResponseAble<T> {
    void sendResponse(DatagramSocket socket, String address, int port, T obj);
}
