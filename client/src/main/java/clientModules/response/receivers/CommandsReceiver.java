package clientModules.response.receivers;

import clientModules.connection.ConnectionModule;
import clientModules.request.sender.CommandsRequestSender;
import clientModules.response.contentHandlers.CommandsHandler;
import requests.CommandDescriptionsRequest;
import responses.CommandDescriptionsResponse;

public class CommandsReceiver {

    public void receiveCommandDescriptions(ConnectionModule module) {
        CommandDescriptionsRequest request = new CommandDescriptionsRequest();
        CommandDescriptionsResponse response = new CommandsRequestSender().sendRequest(module, request);
        new CommandsHandler().handleResponse(response);
    }
}