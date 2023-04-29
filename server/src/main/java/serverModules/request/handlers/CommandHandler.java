package serverModules.request.handlers;

import requests.CommandExecutionRequest;
import serverModules.context.ServerContext;

public class CommandHandler implements HandleRequestAble {

    @Override
    public void handleRequest(ServerContext context) {
        CommandExecutionRequest request = (CommandExecutionRequest) context.getRequest();
        commandsModule.handler.CommandHandler handler = new commandsModule.handler.CommandHandler();
        handler.execute(context.getConnectionModule(), context.getCallerBack(), request);
    }
}