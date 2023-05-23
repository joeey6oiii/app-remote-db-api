package clientModules.response.handlers;

import responses.Response;

import java.io.IOException;
import java.util.HashMap;

/**
 * An interface for all response handler-implementers.
 */

public interface ResponseHandler<T extends Response> {

    /**
     * A method that handles the received responses.
     *
     * @param responses the received responses map
     */

    void handleResponses(HashMap<Integer, T> responses) throws IOException;

}
