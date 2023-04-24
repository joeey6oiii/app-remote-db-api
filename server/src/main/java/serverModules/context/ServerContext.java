package serverModules.context;

import requests.Request;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;

public class ServerContext implements ServerContextContainAble {
    private final ConnectionModule connectionModule;
    private final CallerBack callerBack;
    private final Request request;

    public ServerContext(ConnectionModule connectionModule, CallerBack callerBack, Request request) {
        this.connectionModule = connectionModule;
        this.callerBack = callerBack;
        this.request = request;
    }

    @Override
    public ConnectionModule getConnectionModule() {
        return connectionModule;
    }

    @Override
    public CallerBack getCallerBack() {
        return callerBack;
    }

    @Override
    public Request getRequest() {
        return request;
    }
}





