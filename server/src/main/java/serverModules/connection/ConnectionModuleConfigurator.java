package serverModules.connection;

import java.net.SocketException;

public class ConnectionModuleConfigurator implements InstancingConnectionModuleAble {

    @Override
    public ConnectionModule newInstance(int PORT) {
        try {
            return new ConnectionModule(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}
