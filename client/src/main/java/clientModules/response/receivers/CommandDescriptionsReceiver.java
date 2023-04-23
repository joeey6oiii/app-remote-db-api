package clientModules.response.receivers;

import commands.CommandDescription;
import commands.CommandDescriptionsContainer;
import responses.CommandDescriptionsResponse;
import responses.Response;

import java.util.List;

public class CommandDescriptionsReceiver implements ResponseContentHandleAble {

    @Override
    public void handleResponseContent(Response response) {
        CommandDescriptionsResponse cds = (CommandDescriptionsResponse) response;
        List<CommandDescription> commands = cds.getCommands();
        CommandDescriptionsContainer.setCommandDescriptions(commands);
    }
}