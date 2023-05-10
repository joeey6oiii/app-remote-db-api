package serverModules.request.handlers;

import commandsModule.commands.SingleArgumentCommand;
import commandsModule.handler.CommandHandler;
import requests.SingleArgumentCommandExecutionRequest;
import serverModules.context.ServerContext;

public class ArgumentCommandHandler<T> implements RequestHandler {

    @Override
    public void handleRequest(ServerContext context) {
        SingleArgumentCommandExecutionRequest<T> request = (SingleArgumentCommandExecutionRequest<T>) context.getRequest();
        CommandHandler handler = new CommandHandler();
        SingleArgumentCommand<T> command = (SingleArgumentCommand<T>) handler.getCommandByDescription(request.getDescriptionCommand());
        command.setSingleArgument(request.getArg());
        handler.execute(context.getConnectionModule(), context.getCallerBack(), request);
    }
}