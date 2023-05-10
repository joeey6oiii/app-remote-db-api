package serverModules.response.sender;

import responses.ClientCommandsResponse;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;

public class ClientCommandsResponseSender implements ResponseAble<ClientCommandsResponse> {

    @Override
    public void sendResponse(ConnectionModule module, CallerBack callerBack, ClientCommandsResponse response) {
        new ResponseSender().sendResponse(module, callerBack, response);
    }
}
