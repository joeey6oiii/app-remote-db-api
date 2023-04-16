package serverModules.callerBack;

import java.net.InetAddress;

public class CallerBack {
    private final InetAddress address;
    private final int port;

    public CallerBack(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}