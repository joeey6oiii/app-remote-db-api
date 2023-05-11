package clientModules.response.reader;

import exceptions.ServerUnavailableException;
import responses.Response;
import serializer.ObjectSerializer;

import java.io.IOException;

public class ResponseReader implements ResponseReadAble<Response> {

    @Override
    public Response readResponse(byte[] data) throws IOException, ClassNotFoundException, ServerUnavailableException {
        ObjectSerializer os = new ObjectSerializer();
        Response response = (Response) os.deserialize(data);
        if (response == null) {
            throw new ServerUnavailableException("Server is currently unavailable");
        }
        return response;
    }
}
