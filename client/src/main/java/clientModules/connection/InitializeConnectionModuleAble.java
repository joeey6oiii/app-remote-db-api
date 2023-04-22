package clientModules.connection;

import clientModules.connection.ConnectionModule;

import java.net.SocketAddress;

public interface InitializeConnectionModuleAble {
    ConnectionModule init(SocketAddress address);
    ConnectionModule initConfigureBlocking(SocketAddress address, boolean isBlocking);
}
