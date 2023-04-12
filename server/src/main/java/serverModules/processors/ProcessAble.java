package serverModules.processors;

import java.io.IOException;

public interface ProcessAble<T> {
    T process(T t) throws IOException;
}
