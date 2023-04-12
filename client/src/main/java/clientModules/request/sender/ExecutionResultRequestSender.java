package clientModules.request.sender;

import clientModules.connection.ConnectionModule;
import clientModules.response.ExecutionResultResponse;
import commands.CommandDescription;

public class ExecutionResultRequestSender implements RequestAble<ExecutionResultResponse, CommandDescription> {

    @Override
    public ExecutionResultResponse sendRequest(ConnectionModule module, CommandDescription command) {

        return null;
    }
}
