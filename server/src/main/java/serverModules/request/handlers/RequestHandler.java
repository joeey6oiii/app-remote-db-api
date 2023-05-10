package serverModules.request.handlers;

import serverModules.context.ServerContext;

public interface RequestHandler {
    void handleRequest(ServerContext context);
}
