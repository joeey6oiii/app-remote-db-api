package responses;

import commands.CommandDescription;

import java.io.Serializable;
import java.util.List;

/**
 * A class that represents the server {@link CommandDescription} objects list response.
 */

public class ClientCommandsResponse implements Response, Serializable {
    private final List<CommandDescription> commands;

    /**
     * A constructor for a server {@link CommandDescription} objects list response.
     *
     * @param commands the specified list with the {@link CommandDescription} objects
     */

    public ClientCommandsResponse(List<CommandDescription> commands) {
        this.commands = commands;
    }

    /**
     * A method thar returns <code>List</code> with {@link CommandDescription} objects.
     */

    public List<CommandDescription> getCommands() {
        return commands;
    }
}
