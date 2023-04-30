package clientModules.connection;

import java.io.IOException;

public interface SendDataAble {
    void sendData(byte[] data) throws IOException;
}
