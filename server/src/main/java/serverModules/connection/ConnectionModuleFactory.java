package serverModules.connection;

/**
 * A class that represents a factory of {@link ConnectionModule} objects.
 */

public interface ConnectionModuleFactory {

    /**
     * A method that creates the {@link ConnectionModule} object with the specified port.
     *
     * @param PORT specified port of the server
     * @return server core
     */

    ConnectionModule createConnectionModule(int PORT);

}
