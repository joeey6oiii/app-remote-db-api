package responses;

import java.io.Serializable;

/**
 * A class that represents the server command execution result response.
 */

public class ExecutionResultResponse implements Response, Serializable {
    private final String result;

    /**
     * A constructor for a server command execution result response.
     *
     * @param result the execution result of the command
     */

    public ExecutionResultResponse(String result) {
        this.result = result;
    }

    /**
     * A method that returns the result of the command execution.
     */

    public String getResult() {
        return result;
    }
}
