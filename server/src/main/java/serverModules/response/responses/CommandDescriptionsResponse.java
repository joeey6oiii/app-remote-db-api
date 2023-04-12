package serverModules.response.responses;

import commands.CommandDescription;

import java.util.List;

public class CommandDescriptionsResponse implements Response {
    private final List<CommandDescription> commands;

    public CommandDescriptionsResponse(List<CommandDescription> commands) {
        this.commands = commands;
    }

    public List<CommandDescription> getCommands() {
        return commands;
    }
}
