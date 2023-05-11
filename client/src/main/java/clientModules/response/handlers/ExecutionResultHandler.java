package clientModules.response.handlers;

import responses.ExecutionResultResponse;
import responses.Response;

/**
 * A class that works with the command execution result response.
 */

public class ExecutionResultHandler implements ResponseHandler {

    /**
     * A method that handles the command execution result response and outputs the result.
     *
     * @param response the received response
     */

    @Override
    public void handleResponse(Response response) {
        ExecutionResultResponse resultResponse = (ExecutionResultResponse) response;
        System.out.println(resultResponse.getResult());
    }
}
