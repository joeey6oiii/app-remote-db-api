package clientModules.connection;

import java.net.SocketAddress;

public interface InitializeConnectionModuleAble {
    ConnectionModule init(SocketAddress address);
    ConnectionModule initConfigureBlocking(SocketAddress address, boolean isBlocking);
}
