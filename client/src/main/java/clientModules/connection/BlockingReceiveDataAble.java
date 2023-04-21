package clientModules.connection;

import exceptions.LockStateException;

public interface BlockingReceiveDataAble<T> {
    T blockingReceiveData() throws LockStateException;
}
