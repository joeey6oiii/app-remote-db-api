package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.CommandsRequestSender;
import clientModules.response.handlers.ClientCommandsHandler;
import exceptions.ServerUnavailableException;
import requests.ClientCommandsRequest;
import responses.ClientCommandsResponse;

import java.io.IOException;

public class CommandsReceiver {

    public void receiveCommands(DataTransferConnectionModule module) throws ServerUnavailableException, IOException {
        ClientCommandsRequest request = new ClientCommandsRequest();
        ClientCommandsResponse response = new CommandsRequestSender().sendRequest(module, request);
        new ClientCommandsHandler().handleResponse(response);
    }
}