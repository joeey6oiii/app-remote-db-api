package commandsModule.commands;

import commands.CommandDescription;
import dataBase.Database;

public class SaveCommand implements BaseCommand {
    private final String name = "save";
    private final Database dataBase;

    public SaveCommand(Database dataBase) {
        this.dataBase = dataBase;
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
        return "Saves the collection to a file";
    }

    @Override
    public void execute() {
        dataBase.save();
    }

}
