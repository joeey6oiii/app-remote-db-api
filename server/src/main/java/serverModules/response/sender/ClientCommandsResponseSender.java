package serverModules.response.sender;

import responses.ClientCommandsResponse;
import responses.Response;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;

/**
 * A class that represents the client commands response sender.
 */

public class ClientCommandsResponseSender implements ResponseAble<ClientCommandsResponse> {

    /**
     * A method that calls {@link ResponseSender#sendResponse(ConnectionModule, CallerBack, Response)} method.
     *
     * @param module server core
     * @param callerBack client
     * @param response answer to the client
     */

    // list сериализуем и делим
    @Override
    public void sendResponse(ConnectionModule module, CallerBack callerBack, ClientCommandsResponse response) {
        new ResponseSender().sendResponse(module, callerBack, response);
    }
}
