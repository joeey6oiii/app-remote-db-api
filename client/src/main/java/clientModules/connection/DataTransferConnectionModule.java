package clientModules.connection;

import java.io.IOException;

public interface DataTransferConnectionModule extends ConnectionModule {
    byte[] receiveData() throws IOException;
    void sendData(byte[] data) throws IOException;
}
