package commands;

import java.util.List;

public class CommandDescriptionsContainer {
    private List<CommandDescription> commandDescriptions; // хз делать static или нет

    public void setCommandDescriptions(List<CommandDescription> commandDescriptions) {
        this.commandDescriptions = commandDescriptions;
    }

    public List<CommandDescription> getCommandDescriptions() {
        return this.commandDescriptions;
    }
}
