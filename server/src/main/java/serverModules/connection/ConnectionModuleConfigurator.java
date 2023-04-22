package serverModules.connection;

import java.net.SocketException;

public class ConnectionModuleConfigurator implements InitializeConnectionModuleAble {

    @Override
    public ConnectionModule init(int PORT) {
        try {
            return new ConnectionModule(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}
