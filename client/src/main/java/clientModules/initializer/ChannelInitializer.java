package clientModules.initializer;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

public class ChannelInitializer implements InitializeChannelAble {

    @Override
    public DatagramChannel init() {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            return datagramChannel;
        } catch (IOException e) {
            return null;
        }
    }
}
