package clientModules.response.reader;

import responses.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class ResponseReader implements ResponseReadAble<Response> {

    @Override
    public Response readResponse(ByteBuffer buffer) {
        buffer.flip();
        byte[] data = new byte[buffer.remaining()];
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois;
        buffer.get(data);
        try {
            ois = new ObjectInputStream(bais);
            Response response = (Response) ois.readObject();
            ois.close();
            bais.close();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
