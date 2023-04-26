package commands;

import java.io.Serializable;

public class CommandDescription implements Serializable {
    private final String name;
    private final CommandType type;

    public CommandDescription(String commandName, CommandType type) {
        this.name = commandName;
        this.type = type;
    }

    public String getCommandName() {
        return name;
    }

    public CommandType getType() {
        return this.type;
    }
}
