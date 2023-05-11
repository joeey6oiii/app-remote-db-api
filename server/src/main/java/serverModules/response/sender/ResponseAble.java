package serverModules.response.sender;

import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;

/**
 * An interface for all response sender-implementers.
 *
 * @param <T> concrete response
 */

public interface ResponseAble<T> {

    /**
     * A method that sends response of T type to the client.
     *
     * @param module server core
     * @param callerBack client
     * @param response answer to the client
     */

    void sendResponse(ConnectionModule module, CallerBack callerBack, T response);

}
