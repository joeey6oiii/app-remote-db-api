package clientModules.response.contentHandlers;

import responses.CommandExecutionResultResponse;
import responses.Response;

public class ExecutionResultHandler implements HandleResponseAble {

    @Override
    public void handleResponse(Response response) {
        CommandExecutionResultResponse resultResponse = (CommandExecutionResultResponse) response;
        System.out.println(resultResponse.getResult());
    }
}
