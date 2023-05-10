package clientModules.connection;

import java.io.IOException;

public interface ReceiveDataAble<T> {
    T receiveData() throws IOException;
}
