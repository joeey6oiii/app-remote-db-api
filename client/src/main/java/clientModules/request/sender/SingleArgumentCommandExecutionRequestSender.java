package clientModules.request.sender;

import clientModules.connection.DatagramConnectionModule;
import requests.SingleArgumentCommandExecutionRequest;
import responses.ExecutionResultResponse;

public class SingleArgumentCommandExecutionRequestSender implements RequestAble<ExecutionResultResponse, SingleArgumentCommandExecutionRequest<?>> {

    @Override
    public ExecutionResultResponse sendRequest(DatagramConnectionModule module, SingleArgumentCommandExecutionRequest<?> request) {
        return (ExecutionResultResponse) new RequestSender().sendRequest(module, request);
    }
}
