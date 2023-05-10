package serverModules.connection;

public interface ConnectionModuleFactory {
    ConnectionModule createConnectionModule(int PORT);
}
