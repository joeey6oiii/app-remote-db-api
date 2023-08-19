package serverModules.response.sender;

import response.responses.Response;
import serverModules.callerBack.CallerBack;

/**
 * An interface for all response sender-implementers.
 *
 * @param <T> concrete response
 */

public interface ResponseAble<T extends Response> {

    /**
     * A method that sends response of a T type to the client.
     *
     * @param callerBack client
     * @param response answer to the client
     */

    void sendResponse(CallerBack callerBack, T response);

}
