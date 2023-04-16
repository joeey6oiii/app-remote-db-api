package clientModules.response.reader;

import responses.Response;
import serializer.ObjectSerializer;

import java.io.IOException;

public class ResponseReader implements ResponseReadAble<Response> {

    @Override
    public Response readResponse(byte[] data) {
        ObjectSerializer os = new ObjectSerializer();
        try {
            return (Response) os.deserialize(data);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
