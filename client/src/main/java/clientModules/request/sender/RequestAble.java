package clientModules.request.sender;

import clientModules.connection.DatagramConnectionModule;

public interface RequestAble<T, V> {
    T sendRequest(DatagramConnectionModule module, V value);
}
