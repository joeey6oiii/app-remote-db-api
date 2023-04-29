package responses;

import java.io.Serializable;

public class ExecutionResultResponse implements Response, Serializable {
    private final String result;

    public ExecutionResultResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
