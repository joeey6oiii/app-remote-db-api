package clientModules.request.sender;

import clientModules.connection.ConnectionModule;
import requests.CommandDescriptionsRequest;
import responses.CommandDescriptionsResponse;

public class CommandsRequestSender implements RequestAble<CommandDescriptionsResponse, CommandDescriptionsRequest> {

    @Override
    public CommandDescriptionsResponse sendRequest(ConnectionModule module, CommandDescriptionsRequest request) {
        return (CommandDescriptionsResponse) new RequestSender().sendRequest(module, request);
    }
}
