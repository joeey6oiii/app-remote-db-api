package serverModules.processors;

public interface ProcessAble<T> {
    T process(T t);
}
