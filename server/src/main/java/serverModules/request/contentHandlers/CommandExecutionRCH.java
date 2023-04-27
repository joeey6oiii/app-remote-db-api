package serverModules.request.contentHandlers;

import commandsModule.handler.CommandHandler;
import requests.CommandExecutionRequest;
import serverModules.context.ServerContextContainAble;

public class CommandExecutionRCH implements RequestContentHandleAble {

    @Override
    public void handleRequestContent(ServerContextContainAble context) {
        CommandExecutionRequest request = (CommandExecutionRequest) context.getRequest();
        CommandHandler handler = new CommandHandler();
        handler.execute(context.getConnectionModule(), context.getCallerBack(), request);
    }
}