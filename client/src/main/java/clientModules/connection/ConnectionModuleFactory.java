package clientModules.connection;

import java.net.SocketAddress;

public interface ConnectionModuleFactory {
    ConnectionModule create(SocketAddress address);
}
