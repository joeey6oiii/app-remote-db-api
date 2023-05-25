package clientModules.response.handlers;

import commandsModule.ClientCommandsKeeper;
import response.responses.ClientCommandsResponse;

import java.util.List;

/**
 * A class that works with the commands' response.
 */

public class ClientCommandsHandler implements ResponseHandler<ClientCommandsResponse> {

    /**
     * A method that handles the commands' response and calls the
     * {@link ClientCommandsKeeper#setCommands(List)} method.
     *
     * @param response the received response
     */

    @Override
    public void handleResponse(ClientCommandsResponse response) {
        ClientCommandsKeeper.setCommands(response.getCommands());
    }

}