package responses;

/**
 * An interface for all response-implementers.
 */

public interface Response {

    /**
     * @return total amount of responses from the server
     */

    int getTotalResponsesAmount();

    /**
     * @return current response number
     */
    int getCurrentResponseNumber();

}
