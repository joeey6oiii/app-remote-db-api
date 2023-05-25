package clientModules.response.handlers;

import response.responses.ExecutionResultResponse;

/**
 * A class that works with the "exit" command execution result response.
 */

public class ExitCommandHandler implements ResponseHandler<ExecutionResultResponse> {

    /**
     * A method that handles the "exit" command execution result response and ends the program.
     *
     * @param response the received response
     */

    @Override
    public void handleResponse(ExecutionResultResponse response) {
        System.out.println("Shutting down program...");
        System.exit(0);
    }

}
