package clientModules.connection;

import java.io.IOException;

public interface ConnectionModule {
    void connect() throws IOException;
    void disconnect() throws IOException;
}
