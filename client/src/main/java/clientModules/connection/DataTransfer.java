package clientModules.connection;

import java.io.IOException;

public interface DataTransfer<T> {
    T receiveData() throws IOException;
    void sendData(byte[] data) throws IOException;
}
