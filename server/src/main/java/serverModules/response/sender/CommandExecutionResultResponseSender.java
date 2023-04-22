package serverModules.response.sender;

import responses.CommandExecutionResultResponse;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;

public class CommandExecutionResultResponseSender implements ResponseAble<CommandExecutionResultResponse> {

    @Override
    public void sendResponse(ConnectionModule module, CallerBack callerBack, CommandExecutionResultResponse response) {
        if (response != null) {
            new ResponseSender().sendResponse(module, callerBack, response);
        }
    }
}
