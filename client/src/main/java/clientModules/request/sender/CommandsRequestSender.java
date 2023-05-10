package clientModules.request.sender;

import clientModules.connection.DatagramConnectionModule;
import requests.ClientCommandsRequest;
import responses.ClientCommandsResponse;

public class CommandsRequestSender implements RequestAble<ClientCommandsResponse, ClientCommandsRequest> {

    @Override
    public ClientCommandsResponse sendRequest(DatagramConnectionModule module, ClientCommandsRequest request) {
        return (ClientCommandsResponse) new RequestSender().sendRequest(module, request);
    }
}
