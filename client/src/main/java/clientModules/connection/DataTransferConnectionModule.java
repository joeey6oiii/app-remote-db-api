package clientModules.connection;

import exceptions.ServerUnavailableException;

import java.io.IOException;

public interface DataTransferConnectionModule extends ConnectionModule {
    byte[] receiveData() throws IOException, ServerUnavailableException;
    void sendData(byte[] data) throws IOException, ServerUnavailableException;
}
