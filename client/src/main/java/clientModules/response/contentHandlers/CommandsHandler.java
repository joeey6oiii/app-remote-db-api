package clientModules.response.contentHandlers;

import commands.CommandDescription;
import commandsModule.commands.ClientCommandsKeeper;
import responses.CommandDescriptionsResponse;
import responses.Response;

import java.util.List;

public class CommandsHandler implements HandleResponseAble {

    @Override
    public void handleResponse(Response response) {
        CommandDescriptionsResponse cds = (CommandDescriptionsResponse) response;
        List<CommandDescription> commands = cds.getCommands();
        ClientCommandsKeeper.setCommands(commands);
    }
}