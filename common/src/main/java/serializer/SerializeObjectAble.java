package serializer;

import java.io.IOException;

public interface SerializeObjectAble {
    byte[] serialize(Object object) throws IOException;
    Object deserialize(byte[] data) throws IOException, ClassNotFoundException;
}
