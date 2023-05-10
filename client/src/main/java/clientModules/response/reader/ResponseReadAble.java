package clientModules.response.reader;

import java.io.IOException;

public interface ResponseReadAble<T> {
    T readResponse(byte[] data) throws IOException, ClassNotFoundException;
}
