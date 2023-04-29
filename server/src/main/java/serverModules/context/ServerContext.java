package serverModules.context;

import requests.Request;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;

public class ServerContext {
    private final ConnectionModule connectionModule;
    private final CallerBack callerBack;
    private final Request request;

    public ServerContext(ConnectionModule connectionModule, CallerBack callerBack, Request request) {
        this.connectionModule = connectionModule;
        this.callerBack = callerBack;
        this.request = request;
    }

    public ConnectionModule getConnectionModule() {
        return connectionModule;
    }

    public CallerBack getCallerBack() {
        return callerBack;
    }

    public Request getRequest() {
        return request;
    }

}





