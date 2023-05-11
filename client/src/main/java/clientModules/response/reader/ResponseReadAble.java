package clientModules.response.reader;

import exceptions.ServerUnavailableException;

import java.io.IOException;

public interface ResponseReadAble<T> {
    T readResponse(byte[] data) throws IOException, ClassNotFoundException, ServerUnavailableException;
}
