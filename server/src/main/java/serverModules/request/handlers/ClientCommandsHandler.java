package serverModules.request.handlers;

import commands.CommandDescription;
import commandsModule.ClientCommandsKeeper;
import responses.ClientCommandsResponse;
import serverModules.context.ServerContext;
import serverModules.response.sender.ClientCommandsResponseSender;

import java.util.List;

public class ClientCommandsHandler implements RequestHandler {

    @Override
    public void handleRequest(ServerContext context) {
        List<CommandDescription> commands = ClientCommandsKeeper.getCommands();
        ClientCommandsResponse response = new ClientCommandsResponse(commands);
        new ClientCommandsResponseSender().sendResponse(context.getConnectionModule(), context.getCallerBack(), response);
    }
}
