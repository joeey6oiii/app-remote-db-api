package responses;

import java.io.Serializable;

public class CommandExecutionResultResponse implements Response, Serializable {
    private final String result;

    public CommandExecutionResultResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
