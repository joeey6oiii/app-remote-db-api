package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import requests.SingleArgumentCommandExecutionRequest;
import responses.ExecutionResultResponse;

public class SingleArgumentCommandExecutionRequestSender implements RequestAble<ExecutionResultResponse, SingleArgumentCommandExecutionRequest<?>> {

    @Override
    public ExecutionResultResponse sendRequest(DataTransferConnectionModule module, SingleArgumentCommandExecutionRequest<?> request) {
        return (ExecutionResultResponse) new RequestSender().sendRequest(module, request);
    }
}
