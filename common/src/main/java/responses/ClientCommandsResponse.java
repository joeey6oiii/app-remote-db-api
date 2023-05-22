package responses;

import commands.CommandDescription;

import java.io.Serializable;
import java.util.List;

/**
 * A class that represents the server {@link CommandDescription} objects list response.
 */

public class ClientCommandsResponse implements Response, Serializable {
    private final List<CommandDescription> commands;
    private final int currentResponseNumber;
    private final int totalResponsesAmount;

    /**
     * A constructor for a server {@link CommandDescription} objects list response.
     *
     * @param commands the specified list with the {@link CommandDescription} objects
     */

    public ClientCommandsResponse(List<CommandDescription> commands) {
        this.commands = commands;
        currentResponseNumber = -1;
        totalResponsesAmount = 1;
    }

    /**
     * A constructor for a server {@link CommandDescription} objects list response with current response number and total
     * responses amount.
     *
     * @param commands the specified list with the {@link CommandDescription} objects
     * @param currentResponseNumber the current response number
     * @param totalResponsesAmount total amount of responses from the server
     */

    public ClientCommandsResponse(List<CommandDescription> commands, int currentResponseNumber, int totalResponsesAmount) {
        this.commands = commands;
        this.currentResponseNumber = currentResponseNumber;
        this.totalResponsesAmount = totalResponsesAmount;
    }

    /**
     * A method thar returns <code>List</code> with {@link CommandDescription} objects.
     */

    public List<CommandDescription> getCommands() {
        return commands;
    }

    /**
     * @return total amount of responses from the server
     */

    @Override
    public int getTotalResponsesAmount() {
        return totalResponsesAmount;
    }

    /**
     * @return the current response number
     */

    @Override
    public int getCurrentResponseNumber() {
        return currentResponseNumber;
    }

}
