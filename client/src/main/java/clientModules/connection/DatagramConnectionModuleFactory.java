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
            System.out.println("Unexpected event: Unable to create client connection core");
            System.exit(-99);
        }
        return null;
    }

    public DatagramConnectionModule createConfigureBlocking(SocketAddress address, boolean isBlocking) {
        DatagramChannel datagramChannel;
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(isBlocking);
            return new DatagramConnectionModule(datagramChannel, address);
        } catch (IOException e) {
            System.out.println("Unexpected event: Unable to create client connection core");
            System.exit(-99);
        }
        return null;
    }

    public void configureBlocking(DatagramConnectionModule module, boolean isBlocking) {
        try {
            if (module.getDatagramChannel() != null && module.getDatagramChannel().isOpen()) {
                    module.getDatagramChannel().configureBlocking(isBlocking);
            }
        } catch (IOException e) {
            System.out.println("Unexpected event: Unable to configure client connection core");
            System.exit(-99);
        }
    }
}
