package clientModules.response.handlers;

import commands.CommandDescription;
import commandsModule.ClientCommandsKeeper;
import responses.ClientCommandsResponse;
import responses.Response;

import java.util.List;

public class ClientCommandsHandler implements ResponseHandler {

    @Override
    public void handleResponse(Response response) {
        ClientCommandsResponse cds = (ClientCommandsResponse) response;
        List<CommandDescription> commands = cds.getCommands();
        ClientCommandsKeeper.setCommands(commands);
    }
}