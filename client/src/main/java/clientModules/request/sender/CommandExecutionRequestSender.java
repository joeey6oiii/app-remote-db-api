package clientModules.request.sender;

import clientModules.connection.ConnectionModule;
import requests.CommandExecutionRequest;
import responses.CommandExecutionResultResponse;

public class CommandExecutionRequestSender implements RequestAble<CommandExecutionResultResponse, CommandExecutionRequest> {

    @Override
    public CommandExecutionResultResponse sendRequest(ConnectionModule module, CommandExecutionRequest request) {
        return (CommandExecutionResultResponse) new RequestSender().sendRequest(module, request);
    }
}
