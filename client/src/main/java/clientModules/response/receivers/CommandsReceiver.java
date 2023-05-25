package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.CommandsRequestSender;
import clientModules.response.handlers.ClientCommandsHandler;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;
import requests.ClientCommandsRequest;
import response.responses.ClientCommandsResponse;

import java.io.IOException;

/**
 * A class that represents the commands' receiver.
 */

public class CommandsReceiver {

    /**
     * A method that gets simplified commands response and calls the
     * {@link ClientCommandsHandler#handleResponse(ClientCommandsResponse)})} method.
     *
     * @param module client core
     * @throws ServerUnavailableException if the server was unavailable during sending and receiving operations
     * @throws IOException if failed during I/O operations
     * @throws ResponseTimeoutException if client could not get response from the server during the given time
     */

    public boolean receiveCommands(DataTransferConnectionModule module) throws ServerUnavailableException, IOException, ResponseTimeoutException {
        try {
            new ClientCommandsHandler().handleResponse(new CommandsRequestSender().sendRequest(module, new ClientCommandsRequest()));
            return true;
        } catch (NullPointerException e) {
            throw new IOException("Unexpected error: Empty response received");
        }
    }

}