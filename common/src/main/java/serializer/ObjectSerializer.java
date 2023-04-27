package serializer;

import java.io.*;

public class ObjectSerializer implements SerializeObjectAble<byte[], Object> {

    @Override
    public byte[] serialize(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        oos.flush();
        byte[] data = baos.toByteArray();
        oos.close();
        baos.close();
        return data;
    }

    @Override
    public Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object object = ois.readObject();
        ois.close();
        bais.close();
        return object;
    }
}
