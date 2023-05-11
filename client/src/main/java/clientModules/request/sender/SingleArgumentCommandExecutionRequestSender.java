package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import exceptions.ServerUnavailableException;
import requests.SingleArgumentCommandExecutionRequest;
import responses.ExecutionResultResponse;

import java.io.IOException;

public class SingleArgumentCommandExecutionRequestSender implements RequestAble<ExecutionResultResponse, SingleArgumentCommandExecutionRequest<?>> {

    @Override
    public ExecutionResultResponse sendRequest(DataTransferConnectionModule module, SingleArgumentCommandExecutionRequest<?> request) throws IOException, ServerUnavailableException {
        return (ExecutionResultResponse) new RequestSender().sendRequest(module, request);
    }
}
