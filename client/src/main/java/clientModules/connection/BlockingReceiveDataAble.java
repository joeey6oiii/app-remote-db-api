package clientModules.connection;

import exceptions.LockStateException;

import java.io.IOException;

public interface BlockingReceiveDataAble<T> {
    T blockingReceiveData() throws LockStateException, IOException;
}
