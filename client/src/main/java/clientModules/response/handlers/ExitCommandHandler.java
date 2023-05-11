package clientModules.response.handlers;

import responses.Response;

import java.io.PrintWriter;

/**
 * A class that works with the "exit" command execution result response.
 */

public class ExitCommandHandler implements ResponseHandler {

    /**
     * A method that handles the "exit" command execution result response and ends the program.
     *
     * @param response the received response
     */

    @Override
    public void handleResponse(Response response) {
        System.out.println("Shutting down program...");
        System.exit(0);
    }
}
