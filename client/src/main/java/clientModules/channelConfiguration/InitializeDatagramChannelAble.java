package clientModules.channelConfiguration;

import java.nio.channels.DatagramChannel;

public interface InitializeDatagramChannelAble {
    DatagramChannel init();
    DatagramChannel initConfigureBlocking(boolean isBlocking);
}
