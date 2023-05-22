package responses;

import java.io.Serializable;

/**
 * A class that represents the server command execution result response.
 */

public class ExecutionResultResponse implements Response, Serializable {
    private final String result;
    private final int currentResponseNumber;
    private final int totalResponsesAmount;

    /**
     * A constructor for a server command execution result response.
     *
     * @param result the execution result of the command
     */

    public ExecutionResultResponse(String result) {
        this.result = result;
        currentResponseNumber = -1;
        totalResponsesAmount = 1;
    }

    /**
     * A constructor for a server command execution result response with information about current response number and
     * total responses amount.
     *
     * @param result the execution result of the command
     * @param currentResponseNumber the current response number
     * @param totalResponsesAmount total amount of responses from the server
     */

    public ExecutionResultResponse(String result, int currentResponseNumber, int totalResponsesAmount) {
        this.result = result;
        this.currentResponseNumber = currentResponseNumber;
        this.totalResponsesAmount = totalResponsesAmount;
    }

    /**
     * A method that returns the result of the command execution.
     */

    public String getResult() {
        return result;
    }

    /**
     * @return total amount of responses from the server
     */

    @Override
    public int getTotalResponsesAmount() {
        return totalResponsesAmount;
    }

    /**
     * @return the current response number
     */

    @Override
    public int getCurrentResponseNumber() {
        return currentResponseNumber;
    }

}
