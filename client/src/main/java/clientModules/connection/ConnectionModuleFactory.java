package clientModules.connection;

import java.net.SocketAddress;

public interface ConnectionModuleFactory {
    DatagramConnectionModule create(SocketAddress address);
}
