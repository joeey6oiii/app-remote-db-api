package clientModules.response.contentHandlers;

import commands.CommandDescription;
import commandsModule.commands.CommandDescriptionsKeeper;
import responses.CommandDescriptionsResponse;
import responses.Response;

import java.util.List;

public class CommandDescriptionsRCH implements ResponseContentHandleAble {

    @Override
    public void handleResponseContent(Response response) {
        CommandDescriptionsResponse cds = (CommandDescriptionsResponse) response;
        List<CommandDescription> commands = cds.getCommands();
        CommandDescriptionsKeeper.setCommandDescriptions(commands);
    }
}