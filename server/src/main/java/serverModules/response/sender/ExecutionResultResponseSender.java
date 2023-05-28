package serverModules.response.sender;

import response.responses.CommandExecutionResponse;
import response.responses.Response;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;

/**
 * A class that represents the client command execution response sender.
 */

public class ExecutionResultResponseSender implements ResponseAble<CommandExecutionResponse> {

    /**
     * A method that calls {@link ResponseSender#sendResponse(ConnectionModule, CallerBack, Response)} method.
     *
     * @param module server core
     * @param callerBack client
     * @param response answer to the client
     */

    @Override
    public void sendResponse(ConnectionModule module, CallerBack callerBack, CommandExecutionResponse response) {
        new ResponseSender().sendResponse(module, callerBack, response);
    }

}
