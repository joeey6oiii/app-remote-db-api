package commands;

import java.io.Serializable;
import java.util.List;

public class CommandDescription implements Serializable {
    private final String name;
//    private final List<String> arguments;

    public CommandDescription(String commandName) {
        this.name = commandName;
    }

    public CommandDescription(String commandName, List<String> arguments) {
        this.name = commandName;
//        this.arguments = arguments;
    }

    public String getCommandName() {
        return name;
    }

//    public List<String> getArguments() {
//        return arguments;
//    }
}