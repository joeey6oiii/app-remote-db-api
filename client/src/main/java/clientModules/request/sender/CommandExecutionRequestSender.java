package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import requests.CommandExecutionRequest;
import responses.ExecutionResultResponse;

public class CommandExecutionRequestSender implements RequestAble<ExecutionResultResponse, CommandExecutionRequest> {

    @Override
    public ExecutionResultResponse sendRequest(DataTransferConnectionModule module, CommandExecutionRequest request) {
        return (ExecutionResultResponse) new RequestSender().sendRequest(module, request);
    }
}
