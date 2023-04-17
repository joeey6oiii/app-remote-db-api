package clientModules.connection;

import java.io.IOException;

public interface NonBlockingReceiveDataAble<T> {
    T nonBlockingReceiveData() throws IOException;
}
