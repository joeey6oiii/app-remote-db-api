package serverModules.request.contentHandlers;

import commandsModule.commands.SingleArgumentCommand;
import commandsModule.handler.CommandHandleAble;
import requests.SingleArgumentCommandExecutionRequest;
import serverModules.context.ServerContextContainAble;

public class ArgumentCommandExecutionRCH<T> implements RequestContentHandleAble {

    @Override
    public void handleRequestContent(ServerContextContainAble context, CommandHandleAble handler) {
        SingleArgumentCommandExecutionRequest<T> request = (SingleArgumentCommandExecutionRequest<T>) context.getRequest();
        SingleArgumentCommand<T> command = (SingleArgumentCommand<T>) handler.getCommandByDescription(request.getDescriptionCommand());
        command.setSingleArgument(request.getArg());
        handler.execute(request);
    }
}