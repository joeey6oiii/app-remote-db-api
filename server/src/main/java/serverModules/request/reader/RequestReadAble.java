package serverModules.request.reader;

public interface RequestReadAble<T> {
    T readRequest(byte[] data);
}
