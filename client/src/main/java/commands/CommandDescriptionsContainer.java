package commands;

import java.util.List;

public class CommandDescriptionsContainer {
    private static List<CommandDescription> commands;

    public static void setCommandDescriptions(List<CommandDescription> commands) {
        CommandDescriptionsContainer.commands = commands;
    }

    public static List<CommandDescription> getCommandDescriptions() {
        return commands;
    }
}
