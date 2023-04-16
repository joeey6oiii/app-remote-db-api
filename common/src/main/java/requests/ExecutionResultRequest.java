package requests;

import commands.CommandDescription;

import java.io.Serializable;

public class ExecutionResultRequest implements Request, Serializable {
    private final CommandDescription command;

    public ExecutionResultRequest(CommandDescription command) {
        this.command = command;
    }

    public CommandDescription getCommand() {
        return this.command;
    }
}
