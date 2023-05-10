package clientModules.response.reader;

import responses.Response;
import serializer.ObjectSerializer;

import java.io.IOException;

public class ResponseReader implements ResponseReadAble<Response> {

    @Override
    public Response readResponse(byte[] data) throws IOException, ClassNotFoundException {
        ObjectSerializer os = new ObjectSerializer();
        return (Response) os.deserialize(data);
    }
}
