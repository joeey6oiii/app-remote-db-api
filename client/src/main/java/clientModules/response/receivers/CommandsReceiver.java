package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.CommandsRequestSender;
import clientModules.response.handlers.ClientCommandsHandler;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;
import requests.ClientCommandsRequest;

import java.io.IOException;
import java.util.HashMap;

/**
 * A class that represents the commands' receiver.
 */

public class CommandsReceiver {

    /**
     * A method that gets simplified commands responses and calls the
     * {@link ClientCommandsHandler#handleResponses(HashMap)} method.
     *
     * @param module client core
     * @throws ServerUnavailableException if the server was unavailable during sending and receiving operations
     * @throws IOException if failed during I/O operations
     * @throws ResponseTimeoutException if client could not get response from the server during the given time
     */

    public void receiveCommands(DataTransferConnectionModule module) throws ServerUnavailableException, IOException, ResponseTimeoutException {
        new ClientCommandsHandler().handleResponses(new CommandsRequestSender().sendRequest(module, new ClientCommandsRequest()));
    }

}