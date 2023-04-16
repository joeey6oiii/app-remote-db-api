package clientModules.request.sender;

import clientModules.connection.ConnectionModule;
import requests.ExecutionResultRequest;
import responses.ExecutionResultResponse;

public class ExecutionResultRequestSender implements RequestAble<ExecutionResultResponse, ExecutionResultRequest> {

    @Override
    public ExecutionResultResponse sendRequest(ConnectionModule module, ExecutionResultRequest request) {
        return (ExecutionResultResponse) new RequestSender().sendRequest(module, request);
    }
}
