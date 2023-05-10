package clientModules.connection;

import java.net.SocketAddress;

public interface InitializeConnectionModuleAble {
    DatagramConnectionModule init(SocketAddress address);
    DatagramConnectionModule initConfigureBlocking(SocketAddress address, boolean isBlocking);
}
