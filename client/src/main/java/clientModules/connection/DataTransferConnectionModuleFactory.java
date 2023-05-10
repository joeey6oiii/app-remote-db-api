package clientModules.connection;

import java.net.SocketAddress;

public interface DataTransferConnectionModuleFactory extends ConnectionModuleFactory {
    DataTransferConnectionModule create(SocketAddress address);
}
