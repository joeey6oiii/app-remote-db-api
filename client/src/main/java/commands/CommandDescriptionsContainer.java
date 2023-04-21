package commands;

import java.util.List;

public class CommandDescriptionsContainer {
    private static List<CommandDescription> commandDescriptions;

    public static void setCommandDescriptions(List<CommandDescription> commandDescriptions) {
        CommandDescriptionsContainer.commandDescriptions = commandDescriptions;
    }

    public static List<CommandDescription> getCommandDescriptions() {
        return commandDescriptions;
    }
}
