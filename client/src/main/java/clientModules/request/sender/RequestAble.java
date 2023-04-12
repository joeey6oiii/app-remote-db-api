package clientModules.request.sender;

import clientModules.connection.ConnectionModule;

public interface RequestAble<T, V> {
    T sendRequest(ConnectionModule module, V value);
}
