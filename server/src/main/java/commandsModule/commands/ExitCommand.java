package commandsModule.commands;

import commands.CommandDescription;

public class ExitCommand implements BaseCommand {
    private final String name = "exit";

    public ExitCommand() {}

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public CommandDescription getCommandDescriptionObj() {
        return new CommandDescription(name);
    }

    @Override
    public String describe() {
        return "Closes the program without saving";
    }

    @Override
    public void execute() {
        // new logic
    }

}
