package serverModules.response.responses;

public class ExecutionResultResponse implements Response {
    private final String result;

    public ExecutionResultResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
