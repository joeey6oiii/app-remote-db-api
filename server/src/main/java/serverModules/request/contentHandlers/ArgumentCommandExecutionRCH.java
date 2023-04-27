package serverModules.request.contentHandlers;

import commandsModule.commands.SingleArgumentCommand;
import commandsModule.handler.CommandHandler;
import requests.SingleArgumentCommandExecutionRequest;
import serverModules.context.ServerContextContainAble;

public class ArgumentCommandExecutionRCH<T> implements RequestContentHandleAble {

    @Override
    public void handleRequestContent(ServerContextContainAble context) {
        SingleArgumentCommandExecutionRequest<T> request = (SingleArgumentCommandExecutionRequest<T>) context.getRequest();
        CommandHandler handler = new CommandHandler();
        SingleArgumentCommand<T> command = (SingleArgumentCommand<T>) handler.getCommandByDescription(request.getDescriptionCommand());
        command.setSingleArgument(request.getArg());
        handler.execute(context.getConnectionModule(), context.getCallerBack(), request);
    }
}