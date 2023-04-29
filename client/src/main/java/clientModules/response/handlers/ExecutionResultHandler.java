package clientModules.response.handlers;

import responses.ExecutionResultResponse;
import responses.Response;

public class ExecutionResultHandler implements HandleResponseAble {

    @Override
    public void handleResponse(Response response) {
        ExecutionResultResponse resultResponse = (ExecutionResultResponse) response;
        System.out.println(resultResponse.getResult());
    }
}
