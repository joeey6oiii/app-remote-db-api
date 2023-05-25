package clientModules.response.handlers;

import response.responses.ExecutionResultResponse;

/**
 * A class that works with the command execution result response.
 */

public class ExecutionResultHandler implements ResponseHandler<ExecutionResultResponse> {

    /**
     * A method that handles the command execution result response and outputs the result.
     *
     * @param response the received response
     */

    @Override
    public void handleResponse(ExecutionResultResponse response) {
        System.out.println(response.getResult());
    }

}
