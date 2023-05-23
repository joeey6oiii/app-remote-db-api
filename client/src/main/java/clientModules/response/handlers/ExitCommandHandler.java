package clientModules.response.handlers;

import responses.ExecutionResultResponse;

import java.util.HashMap;

/**
 * A class that works with the "exit" command execution result response.
 */

public class ExitCommandHandler implements ResponseHandler<ExecutionResultResponse> {

    /**
     * A method that handles the "exit" command execution result response and ends the program.
     *
     * @param responses the received responses map
     */

    @Override
    public void handleResponses(HashMap<Integer, ExecutionResultResponse> responses) {
        System.out.println("Shutting down program...");
        System.exit(0);
    }

}
