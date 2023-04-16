package clientModules.response.reader;

public interface ResponseReadAble<T> {
    T readResponse(byte[] data);
}
