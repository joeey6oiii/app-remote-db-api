package clientModules.connection;

import java.net.SocketAddress;

public interface InstancingConnectionModuleAble {
    ConnectionModule newInstance(SocketAddress address);
    ConnectionModule newInstanceConfigureBlocking(SocketAddress address, boolean isBlocking);
}
