package clientModules.response.handlers;

import responses.ExecutionResultResponse;

import java.util.HashMap;

/**
 * A class that works with the command execution result response.
 */

public class ExecutionResultHandler implements ResponseHandler<ExecutionResultResponse> {

    /**
     * A method that handles the command execution result responses and outputs the result.
     *
     * @param responses the received responses map
     */

    @Override
    public void handleResponses(HashMap<Integer, ExecutionResultResponse> responses) {
        int totalResponsesAmount = responses.get(-1).getTotalResponsesAmount();
        ExecutionResultResponse lastResultResponse = responses.get(-1);
        for (int i = 1; i <= totalResponsesAmount - 1; i++) {
            ExecutionResultResponse resultResponse = responses.get(i);
            System.out.print(resultResponse.getResult());
        }
        System.out.print(lastResultResponse.getResult() + "\n");
    }
}
