import java.io.IOException;
import java.nio.channels.DatagramChannel;

public class ChannelConfigurator implements InitializeAble<DatagramChannel> {

    @Override
    public DatagramChannel init() {
        try {
            return DatagramChannel.open();
        } catch (IOException e) {
            return null;
        }
    }
}
