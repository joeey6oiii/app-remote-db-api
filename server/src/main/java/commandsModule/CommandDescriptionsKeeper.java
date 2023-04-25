package commandsModule;

import commands.CommandDescription;

import java.util.ArrayList;
import java.util.List;

public class CommandDescriptionsKeeper {
    private static final List<CommandDescription> commands;

    static {
        commands = new ArrayList<>();

        commands.add(new CommandDescription("add"));
        commands.add(new CommandDescription("info"));
        commands.add(new CommandDescription("show"));
        commands.add(new CommandDescription("clear"));
        commands.add(new CommandDescription("remove_by_id"));
        commands.add(new CommandDescription("help"));
        commands.add(new CommandDescription("exit"));
        commands.add(new CommandDescription("update"));
        commands.add(new CommandDescription("history"));
        commands.add(new CommandDescription("sum_of_height"));
        commands.add(new CommandDescription("average_of_height"));
        commands.add(new CommandDescription("print_field_descending_birthday"));
        commands.add(new CommandDescription("execute_script"));
        commands.add(new CommandDescription("remove_greater"));
        commands.add(new CommandDescription("remove_lower"));
    }

    public static List<CommandDescription> getCommands() {
        return commands;
    }
}
