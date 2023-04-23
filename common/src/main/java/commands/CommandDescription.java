package commands;

import java.io.Serializable;

public class CommandDescription implements Serializable {
    private final String name;
    private String[] args;

    public CommandDescription(String commandName) {
        this.name = commandName;
    }

    public String getCommandName() {
        return name;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
