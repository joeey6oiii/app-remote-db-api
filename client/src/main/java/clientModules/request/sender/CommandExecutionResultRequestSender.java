package clientModules.request.sender;

import clientModules.connection.ConnectionModule;
import requests.CommandExecutionResultRequest;
import responses.CommandExecutionResultResponse;

public class CommandExecutionResultRequestSender implements RequestAble<CommandExecutionResultResponse, CommandExecutionResultRequest> {

    @Override
    public CommandExecutionResultResponse sendRequest(ConnectionModule module, CommandExecutionResultRequest request) {
        return (CommandExecutionResultResponse) new RequestSender().sendRequest(module, request);
    }
}
