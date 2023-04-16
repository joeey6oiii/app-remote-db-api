package responses;

import commands.CommandDescription;

import java.io.Serializable;
import java.util.List;

public class CommandDescriptionsResponse implements Response, Serializable {
    private final List<CommandDescription> commands;

    public CommandDescriptionsResponse(List<CommandDescription> commands) {
        this.commands = commands;
    }

    public List<CommandDescription> getCommands() {
        return commands;
    }
}
