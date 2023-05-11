package serverModules.request.handlers;

import commandsModule.commands.SingleArgumentCommand;
import commandsModule.handler.CommandHandler;
import requests.CommandExecutionRequest;
import requests.SingleArgumentCommandExecutionRequest;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;
import serverModules.context.ServerContext;

/**
 * A class that works with the client single argument command execution request.
 *
 * @param <T> the specified argument
 */

public class ArgumentCommandHandler<T> implements RequestHandler {

    /**
     * A method that handles the client single argument command execution request and calls the
     * {@link CommandHandler#execute(ConnectionModule, CallerBack, CommandExecutionRequest)} method.
     *
     * @param context the specified server settings
     */

    @Override
    public void handleRequest(ServerContext context) {
        SingleArgumentCommandExecutionRequest<T> request = (SingleArgumentCommandExecutionRequest<T>) context.getRequest();
        CommandHandler handler = new CommandHandler();
        SingleArgumentCommand<T> command = (SingleArgumentCommand<T>) handler.getCommandByDescription(request.getDescriptionCommand());
        command.setSingleArgument(request.getArg());
        handler.execute(context.getConnectionModule(), context.getCallerBack(), request);
    }
}