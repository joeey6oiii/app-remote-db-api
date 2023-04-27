package serializer;

import java.io.IOException;

public interface SerializeObjectAble<T, V> {
    T serialize(Object object) throws IOException;
    V deserialize(byte[] data) throws IOException, ClassNotFoundException;
}
