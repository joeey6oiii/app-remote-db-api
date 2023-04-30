package clientModules.connection;

import exceptions.LockStateException;

import java.io.IOException;

public interface NonBlockingReceiveDataAble<T> {
    T nonBlockingReceiveData() throws LockStateException, IOException;
}
