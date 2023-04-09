package serverModules.request;

import java.net.DatagramPacket;

public interface RequestReadAble<T> {
    T readRequest(DatagramPacket packet);
}
