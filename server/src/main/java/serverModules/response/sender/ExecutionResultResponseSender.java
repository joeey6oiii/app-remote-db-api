package serverModules.response.sender;

import responses.ExecutionResultResponse;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;

public class ExecutionResultResponseSender implements ResponseAble<ExecutionResultResponse> {

    @Override
    public void sendResponse(ConnectionModule module, CallerBack callerBack, ExecutionResultResponse response) {
        if (response != null) {
            new ResponseSender().sendResponse(module, callerBack, response);
        }
    }
}
