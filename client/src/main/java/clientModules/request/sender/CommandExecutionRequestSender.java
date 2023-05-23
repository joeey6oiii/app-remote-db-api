package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;
import requests.CommandExecutionRequest;
import requests.Request;
import responses.ExecutionResultResponse;
import responses.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents the command execution request sender.
 */

public class CommandExecutionRequestSender implements RequestAble<HashMap<Integer, ExecutionResultResponse>, CommandExecutionRequest> {

    /**
     * A method that calls {@link RequestSender#sendRequest(DataTransferConnectionModule, Request)} method.
     *
     * @param module client core
     * @param request concrete request
     * @throws IOException if failed during I/O operations
     * @throws ServerUnavailableException if the server was unavailable during sending and receiving operations
     * @return command execution result responses map
     */

    @Override
    public HashMap<Integer, ExecutionResultResponse> sendRequest(DataTransferConnectionModule module, CommandExecutionRequest request) throws IOException, ServerUnavailableException, ResponseTimeoutException {
        HashMap<Integer, Response> originalMap = new RequestSender().sendRequest(module, request);

        HashMap<Integer, ExecutionResultResponse> convertedMap = new HashMap<>();
        for (Map.Entry<Integer, Response> entry : originalMap.entrySet()) {
            Integer key = entry.getKey();
            Response value = entry.getValue();

            if (value instanceof ExecutionResultResponse executionResultResponse) {
                convertedMap.put(key, executionResultResponse);
            }
        }

        return convertedMap;
    }

}
