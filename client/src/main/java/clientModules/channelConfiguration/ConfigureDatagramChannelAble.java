package clientModules.channelConfiguration;

import clientModules.connection.ConnectionModule;

public interface ConfigureDatagramChannelAble {
    void configureBlocking(ConnectionModule module, boolean isBlocking);
}
