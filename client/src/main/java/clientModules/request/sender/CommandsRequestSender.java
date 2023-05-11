package clientModules.request.sender;

import clientModules.connection.DataTransferConnectionModule;
import exceptions.ServerUnavailableException;
import requests.ClientCommandsRequest;
import responses.ClientCommandsResponse;

import java.io.IOException;

public class CommandsRequestSender implements RequestAble<ClientCommandsResponse, ClientCommandsRequest> {

    @Override
    public ClientCommandsResponse sendRequest(DataTransferConnectionModule module, ClientCommandsRequest request) throws IOException, ServerUnavailableException {
        return (ClientCommandsResponse) new RequestSender().sendRequest(module, request);
    }
}
