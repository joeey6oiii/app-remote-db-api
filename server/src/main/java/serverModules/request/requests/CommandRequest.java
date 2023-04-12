package serverModules.request.requests;

import commands.CommandDescription;

import java.io.Serializable;

public class CommandRequest implements Request, Serializable {
    private final CommandDescription command;

    public CommandRequest(CommandDescription command) {
        this.command = command;
    }

    public CommandDescription getCommand() {
        return this.command;
    }
}
