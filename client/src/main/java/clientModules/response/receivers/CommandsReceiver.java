package clientModules.response.receivers;

import clientModules.connection.DatagramConnectionModule;
import clientModules.request.sender.CommandsRequestSender;
import clientModules.response.handlers.ClientCommandsHandler;
import requests.ClientCommandsRequest;
import responses.ClientCommandsResponse;

public class CommandsReceiver {

    public void receiveCommands(DatagramConnectionModule module) {
        ClientCommandsRequest request = new ClientCommandsRequest();
        ClientCommandsResponse response = new CommandsRequestSender().sendRequest(module, request);
        new ClientCommandsHandler().handleResponse(response);
    }
}