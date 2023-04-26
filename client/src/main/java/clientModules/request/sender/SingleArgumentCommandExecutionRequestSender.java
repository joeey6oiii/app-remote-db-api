package clientModules.request.sender;

import clientModules.connection.ConnectionModule;
import requests.SingleArgumentCommandExecutionRequest;
import responses.CommandExecutionResultResponse;

public class SingleArgumentCommandExecutionRequestSender implements RequestAble<CommandExecutionResultResponse, SingleArgumentCommandExecutionRequest<?>> {

    @Override
    public CommandExecutionResultResponse sendRequest(ConnectionModule module, SingleArgumentCommandExecutionRequest<?> request) {
        return (CommandExecutionResultResponse) new RequestSender().sendRequest(module, request);
    }
}
