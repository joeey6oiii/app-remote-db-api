package commandsModule.commands;

import commands.CommandDescription;

import java.util.List;

public class CommandDescriptionsKeeper {
    private static List<CommandDescription> commands;

    public static void setCommandDescriptions(List<CommandDescription> commands) {
        CommandDescriptionsKeeper.commands = commands;
    }

    public static List<CommandDescription> getCommandDescriptions() {
        return commands;
    }
}
