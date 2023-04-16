package serverModules.request.reader;

import java.net.DatagramPacket;

public interface RequestReadAble<T> {
    T readRequest(DatagramPacket packet);
}
