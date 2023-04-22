package serverModules.request.contentHandlers;

import serverModules.context.ServerContext;

public interface RequestContentHandler {
    void handleRequestContent(ServerContext context);
}
