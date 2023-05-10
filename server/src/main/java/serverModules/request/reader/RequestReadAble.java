package serverModules.request.reader;

import java.io.IOException;

public interface RequestReadAble<T> {
    T readRequest(byte[] data) throws IOException, ClassNotFoundException;
}
