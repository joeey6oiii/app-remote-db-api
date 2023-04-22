package serverModules.request.requestContentHandlers;

import serverModules.context.ServerContext;

public interface RequestContentHandler {
    void handleRequestContent(ServerContext context);
}
