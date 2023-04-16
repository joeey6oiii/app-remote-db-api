package clientModules.response.reader;

import java.nio.ByteBuffer;

public interface ResponseReadAble<T> {
    T readResponse(ByteBuffer buffer);
}
