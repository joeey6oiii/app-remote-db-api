package serverModules.request.contentHandlers;

import commandsModule.handler.CommandHandleAble;
import requests.CommandExecutionRequest;
import serverModules.context.ServerContextContainAble;

public class CommandExecutionRCH implements RequestContentHandleAble {

    @Override
    public void handleRequestContent(ServerContextContainAble context, CommandHandleAble handler) {
        CommandExecutionRequest request = (CommandExecutionRequest) context.getRequest();
        handler.execute(request);
    }
}