package serverModules.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.SocketException;

public class DatagramConnectionModuleFactory implements ConnectionModuleFactory {
    private static final Logger logger = LogManager.getLogger("logger.DatagramConnectionModuleFactory");

    @Override
    public DatagramConnectionModule createConnectionModule(int PORT) {
        try {
            return new DatagramConnectionModule(PORT);
        } catch (SocketException e) {
            logger.fatal("Failed to create server core", e);
            System.exit(-99);
        }
        return null;
    }
}
