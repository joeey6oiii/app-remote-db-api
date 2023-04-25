package serverModules.request.contentHandlers;

import commands.CommandDescription;
import commandsModule.CommandDescriptionsKeeper;
import commandsModule.handler.CommandHandleAble;
import responses.CommandDescriptionsResponse;
import serverModules.context.ServerContextContainAble;
import serverModules.response.sender.CommandDescriptionsResponseSender;

import java.util.List;

public class CommandDescriptionsRCH implements RequestContentHandleAble {

    @Override
    public void handleRequestContent(ServerContextContainAble context, CommandHandleAble handler) {
        List<CommandDescription> commands = CommandDescriptionsKeeper.getCommands();
        CommandDescriptionsResponse response = new CommandDescriptionsResponse(commands);
        new CommandDescriptionsResponseSender().sendResponse(context.getConnectionModule(), context.getCallerBack(), response);
    }
}
