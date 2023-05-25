package serverModules.request.handlers;

import commandsModule.ClientCommandsKeeper;
import response.responses.ClientCommandsResponse;
import serverModules.context.ServerContext;
import serverModules.response.sender.ClientCommandsResponseSender;

/**
 * A class that works with the client commands request.
 */

public class ClientCommandsHandler implements RequestHandler {

    /**
     * A method that handles the client commands request and sends the client commands response.
     *
     * @param context the specified server settings
     */

    @Override
    public void handleRequest(ServerContext context) {
        new ClientCommandsResponseSender().sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new ClientCommandsResponse(ClientCommandsKeeper.getCommands()));
    }

}
