package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import exceptions.ServerUnavailableException;
import requests.ClientCommandsRequest;
import requests.Request;
import responses.ClientCommandsResponse;

import java.io.IOException;

/**
 * A class that represents the simplified commands' request sender.
 */

public class CommandsRequestSender implements RequestAble<ClientCommandsResponse, ClientCommandsRequest> {

    /**
     * A method that calls {@link RequestSender#sendRequest(DataTransferConnectionModule, Request)} method.
     *
     * @param module client core
     * @param request concrete request
     * @throws IOException if failed during I/O operations
     * @throws ServerUnavailableException if the server was unavailable during sending and receiving operations
     * @return list of simplified commands
     */

    @Override
    public ClientCommandsResponse sendRequest(DataTransferConnectionModule module, ClientCommandsRequest request) throws IOException, ServerUnavailableException {
        return (ClientCommandsResponse) new RequestSender().sendRequest(module, request);
    }
}
