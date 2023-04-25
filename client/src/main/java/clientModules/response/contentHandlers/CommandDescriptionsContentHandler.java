package clientModules.response.contentHandlers;

import commands.CommandDescription;
import commands.CommandDescriptionsKeeper;
import responses.CommandDescriptionsResponse;
import responses.Response;

import java.util.List;

public class CommandDescriptionsContentHandler implements ResponseContentHandleAble {

    @Override
    public void handleResponseContent(Response response) {
        CommandDescriptionsResponse cds = (CommandDescriptionsResponse) response;
        List<CommandDescription> commands = cds.getCommands();
        CommandDescriptionsKeeper.setCommandDescriptions(commands);
    }
}