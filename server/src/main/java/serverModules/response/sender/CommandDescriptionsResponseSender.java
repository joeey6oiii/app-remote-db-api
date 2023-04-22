package serverModules.response.sender;

import responses.CommandDescriptionsResponse;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;

public class CommandDescriptionsResponseSender implements ResponseAble<CommandDescriptionsResponse> {

    @Override
    public void sendResponse(ConnectionModule module, CallerBack callerBack, CommandDescriptionsResponse response) {
        if (response != null) {
            new ResponseSender().sendResponse(module, callerBack, response);
        }
    }
}
