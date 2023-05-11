package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import exceptions.ServerUnavailableException;
import requests.CommandExecutionRequest;
import responses.ExecutionResultResponse;

import java.io.IOException;

public class CommandExecutionRequestSender implements RequestAble<ExecutionResultResponse, CommandExecutionRequest> {

    @Override
    public ExecutionResultResponse sendRequest(DataTransferConnectionModule module, CommandExecutionRequest request) throws IOException, ServerUnavailableException {
        return (ExecutionResultResponse) new RequestSender().sendRequest(module, request);
    }
}
