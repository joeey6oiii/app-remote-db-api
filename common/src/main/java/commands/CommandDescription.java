package commands;

import java.io.Serializable;
import java.util.List;

public class CommandDescription implements Serializable {
    private final String name;

    public CommandDescription(String commandName) {
        this.name = commandName;
    }

    public CommandDescription(String commandName, List<String> arguments) {
        this.name = commandName;
    }

    public String getCommandName() {
        return name;
    }
}
