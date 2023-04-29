package serverModules.request.handlers;

import serverModules.context.ServerContext;

public interface HandleRequestAble {
    void handleRequest(ServerContext context);
}
