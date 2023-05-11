package clientModules.response.handlers;

import responses.Response;

/**
 * An interface for all response handler-implementers.
 */

public interface ResponseHandler {

    /**
     * A method that handles the received response.
     *
     * @param response the received response
     */

    void handleResponse(Response response);

}
