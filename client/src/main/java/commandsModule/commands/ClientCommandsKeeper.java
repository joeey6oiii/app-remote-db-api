package commandsModule.commands;

import commands.CommandDescription;

import java.util.List;

public class ClientCommandsKeeper {
    private static List<CommandDescription> commands;

    public static void setCommands(List<CommandDescription> commands) {
        ClientCommandsKeeper.commands = commands;
    }

    public static List<CommandDescription> getCommands() {
        return commands;
    }
}
