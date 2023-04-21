package clientModules.connection;

import exceptions.LockStateException;

public interface NonBlockingReceiveDataAble<T> {
    T nonBlockingReceiveData() throws LockStateException;
}
