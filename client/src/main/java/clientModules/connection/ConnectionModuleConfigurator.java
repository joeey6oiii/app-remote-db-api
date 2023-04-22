package clientModules.connection;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class ConnectionModuleConfigurator implements InstancingConnectionModuleAble, ConfigureConnectionModuleAble {

    @Override
    public ConnectionModule newInstance(SocketAddress address) {
        try {
            return new ConnectionModule(DatagramChannel.open(), address);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ConnectionModule newInstanceConfigureBlocking(SocketAddress address, boolean isBlocking) {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(isBlocking);
            return new ConnectionModule(datagramChannel, address);
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
