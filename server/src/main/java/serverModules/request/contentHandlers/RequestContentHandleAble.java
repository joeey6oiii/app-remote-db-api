package serverModules.request.contentHandlers;

import commandsModule.handler.CommandHandleAble;
import serverModules.context.ServerContextContainAble;

public interface RequestContentHandleAble {
    void handleRequestContent(ServerContextContainAble context, CommandHandleAble handler);
}
