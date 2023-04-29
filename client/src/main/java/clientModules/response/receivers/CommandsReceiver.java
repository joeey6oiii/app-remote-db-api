package clientModules.response.receivers;

import clientModules.connection.ConnectionModule;
import clientModules.request.sender.CommandsRequestSender;
import clientModules.response.handlers.CommandsHandler;
import requests.ClientCommandsRequest;
import responses.ClientCommandsResponse;

public class CommandsReceiver {

    public void receiveCommands(ConnectionModule module) {
        ClientCommandsRequest request = new ClientCommandsRequest();
        ClientCommandsResponse response = new CommandsRequestSender().sendRequest(module, request);
        new CommandsHandler().handleResponse(response);
    }
}