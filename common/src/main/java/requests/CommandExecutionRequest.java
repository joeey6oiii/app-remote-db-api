package requests;

import commands.CommandDescription;

import java.io.Serializable;

public class CommandExecutionRequest implements Request, Serializable {
    private final CommandDescription command;
    private final String[] args;

    public CommandExecutionRequest(CommandDescription command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public CommandDescription getDescriptionCommand() {
        return this.command;
    }

    public String[] getArgs() {
        return this.args;
    }
}
