package clientModules.response.receivers;

import clientModules.connection.ConnectionModule;
import clientModules.request.sender.CommandExecutionRequestSender;
import clientModules.response.contentHandlers.CommandExecutionResultRCH;
import commands.CommandDescription;
import requests.CommandExecutionRequest;
import responses.CommandExecutionResultResponse;

public class CommandResultReceiver implements CommandReceiver {

    @Override
    public void receiveCommand(CommandDescription cmd, String[] arr, ConnectionModule module) {
        CommandExecutionRequest request = new CommandExecutionRequest(cmd, arr);
        CommandExecutionResultResponse resultResponse = new CommandExecutionRequestSender().sendRequest(module, request);
        new CommandExecutionResultRCH().handleResponseContent(resultResponse);
    }
}
