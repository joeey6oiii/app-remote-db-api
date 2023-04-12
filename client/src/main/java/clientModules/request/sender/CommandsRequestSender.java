package clientModules.request.sender;

import clientModules.connection.ConnectionModule;
import clientModules.response.CommandDescriptionsResponse;
import commands.CommandDescription;

import java.util.ArrayList;

public class CommandsRequestSender implements RequestAble<CommandDescriptionsResponse, ArrayList<CommandDescription>> {

    @Override
    public CommandDescriptionsResponse sendRequest(ConnectionModule module, ArrayList<CommandDescription> list) {

        return null;
    }
}
