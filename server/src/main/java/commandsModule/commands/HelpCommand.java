package commandsModule.commands;

import commands.CommandDescription;
import commandsModule.master.CommandContext;
import dataBase.DataBase;

import java.io.IOException;

public class HelpCommand implements BaseCommand {
    private final String name = "help";
    private final DataBase dataBase;
    private final CommandContext context;

    public HelpCommand(DataBase dataBase, CommandContext context) {
        this.dataBase = dataBase;
        this.context = context;
    }

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
        return "Displays information about console application commands";
    }

    @Override
    public void execute() throws IOException {
        dataBase.help(context.getCommands());
    }

}
