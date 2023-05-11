package clientModules.response.handlers;

import commands.CommandDescription;
import commandsModule.ClientCommandsKeeper;
import commandsModule.handler.CommandHandler;
import requests.CommandExecutionRequest;
import responses.ClientCommandsResponse;
import responses.Response;

import java.util.List;

/**
 * A class that works with the commands' response.
 */

public class ClientCommandsHandler implements ResponseHandler {

    /**
     * A method that handles the commands' response and calls the
     * {@link ClientCommandsKeeper#setCommands(List)} method.
     *
     * @param response the received response
     */

    @Override
    public void handleResponse(Response response) {
        ClientCommandsResponse cds = (ClientCommandsResponse) response;
        List<CommandDescription> commands = cds.getCommands();
        ClientCommandsKeeper.setCommands(commands);
    }
}