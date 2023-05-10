package serverModules.connection;

import java.net.SocketException;

public class DatagramConnectionModuleFactory implements ConnectionModuleFactory {

    @Override
    public DatagramConnectionModule createConnectionModule(int PORT) {
        try {
            return new DatagramConnectionModule(PORT);
        } catch (SocketException e) {
            e.printStackTrace(); // fix
        }
        return null; // fix
    }
}
