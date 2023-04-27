package clientModules.response.receivers;

import clientModules.connection.ConnectionModule;
import clientModules.request.sender.CommandDescriptionsRequestSender;
import clientModules.response.contentHandlers.CommandDescriptionsRCH;
import commands.CommandDescription;
import commandsModule.commands.CommandDescriptionsKeeper;
import requests.CommandDescriptionsRequest;
import responses.CommandDescriptionsResponse;
import responses.Response;

import java.util.List;

public class CommandDescriptionsReceiver {

    public void receiveCommandDescriptions(ConnectionModule module) {
        CommandDescriptionsRequest request = new CommandDescriptionsRequest();
        CommandDescriptionsResponse response = new CommandDescriptionsRequestSender().sendRequest(module, request);
        new CommandDescriptionsRCH().handleResponseContent(response);
    }
}