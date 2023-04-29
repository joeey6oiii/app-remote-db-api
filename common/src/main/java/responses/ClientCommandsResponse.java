package responses;

import commands.CommandDescription;

import java.io.Serializable;
import java.util.List;

public class ClientCommandsResponse implements Response, Serializable {
    private final List<CommandDescription> commands;

    public ClientCommandsResponse(List<CommandDescription> commands) {
        this.commands = commands;
    }

    public List<CommandDescription> getCommands() {
        return commands;
    }
}
