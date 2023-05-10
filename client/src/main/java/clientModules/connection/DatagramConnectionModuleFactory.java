package clientModules.connection;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class DatagramConnectionModuleFactory implements DataTransferConnectionModuleFactory {

    @Override
    public DatagramConnectionModule create(SocketAddress address) {
        try {
            return new DatagramConnectionModule(DatagramChannel.open(), address);
        } catch (IOException e) {
            // fix
        }
        return null; // fix
    }

    public DatagramConnectionModule createConfigureBlocking(SocketAddress address, boolean isBlocking) {
        DatagramChannel datagramChannel;
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(isBlocking);
            return new DatagramConnectionModule(datagramChannel, address);
        } catch (IOException e) {
            // fix
        }
        return null; // fix
    }

    public void configureBlocking(DatagramConnectionModule module, boolean isBlocking) {
        try {
            if (module.getDatagramChannel() != null && module.getDatagramChannel().isOpen()) {
                    module.getDatagramChannel().configureBlocking(isBlocking);
            }
        } catch (IOException e) {
            // fix
        }
    }
}
