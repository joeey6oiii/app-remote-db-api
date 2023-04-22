package serverModules.response.sender;

import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;

import java.net.InetAddress;

public interface ResponseAble<T> {
    void sendResponse(ConnectionModule module, CallerBack callerBack, T response);
}
