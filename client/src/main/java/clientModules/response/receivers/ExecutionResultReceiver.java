package clientModules.response.receivers;

import clientModules.connection.DatagramConnectionModule;
import clientModules.request.sender.CommandExecutionRequestSender;
import clientModules.response.handlers.ExecutionResultHandler;
import commands.CommandDescription;
import requests.CommandExecutionRequest;
import responses.ExecutionResultResponse;

public class ExecutionResultReceiver implements CommandReceiver {

    @Override
    public void receiveCommand(CommandDescription cmd, String[] args, DatagramConnectionModule module) {
        CommandExecutionRequest request = new CommandExecutionRequest(cmd, args);
        ExecutionResultResponse resultResponse = new CommandExecutionRequestSender().sendRequest(module, request);
        new ExecutionResultHandler().handleResponse(resultResponse);
    }
}
