package requests;

import commands.CommandDescription;

import java.io.Serializable;

public class CommandExecutionResultRequest implements Request, Serializable {
    private final CommandDescription command;

    public CommandExecutionResultRequest(CommandDescription command) {
        this.command = command;
    }

    public CommandDescription getCommand() {
        return this.command;
    }
}
