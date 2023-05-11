package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.CommandsRequestSender;
import clientModules.response.handlers.ClientCommandsHandler;
import exceptions.ServerUnavailableException;
import requests.ClientCommandsRequest;
import responses.ClientCommandsResponse;
import responses.Response;

import java.io.IOException;

/**
 * A class that represents the commands' receiver.
 */

public class CommandsReceiver {

    /**
     * A method that receives the simplified commands' list, sends request to a server,
     * gets response and calls the {@link ClientCommandsHandler#handleResponse(Response)} method.
     *
     * @param module client core
     * @throws ServerUnavailableException if the server was unavailable during sending and receiving operations
     * @throws IOException if failed during I/O operations
     */

    public void receiveCommands(DataTransferConnectionModule module) throws ServerUnavailableException, IOException {
        ClientCommandsRequest request = new ClientCommandsRequest();
        ClientCommandsResponse response = new CommandsRequestSender().sendRequest(module, request);
        new ClientCommandsHandler().handleResponse(response);
    }
}