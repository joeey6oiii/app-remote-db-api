package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;
import requests.ClientCommandsRequest;
import requests.Request;
import responses.ClientCommandsResponse;
import responses.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents the simplified commands' request sender.
 */

public class CommandsRequestSender implements RequestAble<HashMap<Integer, ClientCommandsResponse>, ClientCommandsRequest> {

    /**
     * A method that calls {@link RequestSender#sendRequest(DataTransferConnectionModule, Request)} method.
     *
     * @param module client core
     * @param request concrete request
     * @throws IOException if failed during I/O operations
     * @throws ServerUnavailableException if the server was unavailable during sending and receiving operations
     * @return commands responses map
     */

    @Override
    public HashMap<Integer, ClientCommandsResponse> sendRequest(DataTransferConnectionModule module, ClientCommandsRequest request) throws IOException, ServerUnavailableException, ResponseTimeoutException {
        HashMap<Integer, Response> originalMap = new RequestSender().sendRequest(module, request);

        HashMap<Integer, ClientCommandsResponse> convertedMap = new HashMap<>();
        for (Map.Entry<Integer, Response> entry : originalMap.entrySet()) {
            Integer key = entry.getKey();
            Response value = entry.getValue();

            if (value instanceof ClientCommandsResponse clientCommandsResponse) {
                convertedMap.put(key, clientCommandsResponse);
            }
        }

        return convertedMap;
    }

}
