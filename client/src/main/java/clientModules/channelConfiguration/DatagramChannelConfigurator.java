package clientModules.channelConfiguration;

import clientModules.connection.ConnectionModule;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

public class DatagramChannelConfigurator implements InitializeDatagramChannelAble, ConfigureDatagramChannelAble {

    @Override
    public DatagramChannel init() {
        try {
            return DatagramChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DatagramChannel initConfigureBlocking(boolean isBlocking) {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(isBlocking);
            return datagramChannel;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void configureBlocking(ConnectionModule module, boolean isBlocking) {
        try {
            if (module.getDatagramChannel() != null) {
                if (module.getDatagramChannel().isOpen()) {
                    module.getDatagramChannel().configureBlocking(isBlocking);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
