package clientModules.connection;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class ConnectionModuleConfigurator implements InitializeConnectionModuleAble {

    @Override
    public DatagramConnectionModule init(SocketAddress address) {
        try {
            return new DatagramConnectionModule(DatagramChannel.open(), address);
        } catch (IOException e) {

        }
        return null;
    }

    @Override
    public DatagramConnectionModule initConfigureBlocking(SocketAddress address, boolean isBlocking) {
        DatagramChannel datagramChannel;
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(isBlocking);
            return new DatagramConnectionModule(datagramChannel, address);
        } catch (IOException e) {

        }
        return null;
    }

    public void configureBlocking(DatagramConnectionModule module, boolean isBlocking) {
        try {
            if (module.getDatagramChannel() != null) {
                if (module.getDatagramChannel().isOpen()) {
                    module.getDatagramChannel().configureBlocking(isBlocking);
                }
            }
        } catch (IOException e) {

        }
    }
}
