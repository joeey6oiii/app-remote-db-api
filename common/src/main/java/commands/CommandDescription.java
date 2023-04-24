package commands;

import java.io.Serializable;

public class CommandDescription implements Serializable {
    private final String name;

    public CommandDescription(String commandName) {
        this.name = commandName;
    }

    public String getCommandName() {
        return name;
    }
}
