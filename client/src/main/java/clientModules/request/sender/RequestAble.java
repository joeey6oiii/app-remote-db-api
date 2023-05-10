package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;

public interface RequestAble<T, V> {
    T sendRequest(DataTransferConnectionModule module, V value);
}
