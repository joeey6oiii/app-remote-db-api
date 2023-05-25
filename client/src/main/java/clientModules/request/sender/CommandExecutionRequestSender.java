package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;
import requests.CommandExecutionRequest;
import requests.Request;
import response.responses.ExecutionResultResponse;

import java.io.IOException;

/**
 * A class that represents the command execution request sender.
 */

public class CommandExecutionRequestSender implements RequestAble<ExecutionResultResponse, CommandExecutionRequest> {

    /**
     * A method that calls {@link RequestSender#sendRequest(DataTransferConnectionModule, Request)} method.
     *
     * @param module client core
     * @param request concrete request
     * @throws IOException if failed during I/O operations
     * @throws ServerUnavailableException if the server was unavailable during sending and receiving operations
     * @return command execution result response
     */

    @Override
    public ExecutionResultResponse sendRequest(DataTransferConnectionModule module, CommandExecutionRequest request) throws IOException, ServerUnavailableException, ResponseTimeoutException {
        return (ExecutionResultResponse) new RequestSender().sendRequest(module, request);
    }

}
