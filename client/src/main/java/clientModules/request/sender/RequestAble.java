package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import exceptions.ServerUnavailableException;

import java.io.IOException;

public interface RequestAble<T, V> {
    T sendRequest(DataTransferConnectionModule module, V value) throws IOException, ServerUnavailableException;
}
