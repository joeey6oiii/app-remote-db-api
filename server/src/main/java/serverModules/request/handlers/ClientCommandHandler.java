package serverModules.request.handlers;

import commandsModule.handler.CommandHandler;
import requests.CommandExecutionRequest;
import serverModules.context.ServerContext;

public class ClientCommandHandler implements RequestHandler {

    @Override
    public void handleRequest(ServerContext context) {
        CommandExecutionRequest request = (CommandExecutionRequest) context.getRequest();
        new CommandHandler().execute(context.getConnectionModule(), context.getCallerBack(), request);
    }
}