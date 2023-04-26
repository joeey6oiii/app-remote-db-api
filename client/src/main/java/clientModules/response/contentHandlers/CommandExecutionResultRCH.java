package clientModules.response.contentHandlers;

import responses.CommandExecutionResultResponse;
import responses.Response;

public class CommandExecutionResultRCH implements ResponseContentHandleAble {

    @Override
    public void handleResponseContent(Response response) {
        CommandExecutionResultResponse resultResponse = (CommandExecutionResultResponse) response;
        System.out.println(resultResponse.getResult());
    }
}
