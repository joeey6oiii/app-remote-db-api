package responses;

import commands.CommandDescription;

import java.io.Serializable;

/**
 * A class that represents the server {@link CommandDescription} objects list response.
 */

public class ClientCommandsResponse implements Response, Serializable {
    private final byte[] partOfList;
    private final int serializedListSize;
    private final int currentResponseNumber;
    private final int totalResponsesAmount;

    /**
     * A constructor for a server {@link CommandDescription} objects list response.
     *
     * @param partOfList the specified serialized part of list with the {@link CommandDescription} objects
     */

    public ClientCommandsResponse(byte[] partOfList, int serializedListSize) {
        this.partOfList = partOfList;
        this.serializedListSize = serializedListSize;
        currentResponseNumber = -1;
        totalResponsesAmount = 1;
    }

    /**
     * A constructor for a server {@link CommandDescription} objects list response with current response number and total
     * responses amount.
     *
     * @param partOfList the specified serialized part of list with the {@link CommandDescription} objects
     * @param currentResponseNumber the current response number
     * @param totalResponsesAmount total amount of responses from the server
     */

    public ClientCommandsResponse(byte[] partOfList, int serializedListSize, int currentResponseNumber, int totalResponsesAmount) {
        this.partOfList = partOfList;
        this.serializedListSize = serializedListSize;
        this.currentResponseNumber = currentResponseNumber;
        this.totalResponsesAmount = totalResponsesAmount;
    }

    /**
     * A method thar returns <code>byte[]</code> array with serialized part of list with {@link CommandDescription} objects.
     */

    public byte[] getPartOfList() {
        return this.partOfList;
    }

    /**
     * @return size of the serialized list
     */

    public int getSerializedListSize() {
        return this.serializedListSize;
    }

    /**
     * @return total amount of responses from the server
     */

    @Override
    public int getTotalResponsesAmount() {
        return this.totalResponsesAmount;
    }

    /**
     * @return the current response number
     */

    @Override
    public int getCurrentResponseNumber() {
        return this.currentResponseNumber;
    }

}
