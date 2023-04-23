package commandsModule.commands;

import commands.CommandDescription;
import dataBase.Database;

public class AddCommand implements BaseCommand {
    private final String name = "add";
    private final Database dataBase;

    public AddCommand(Database dataBase) {
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
        return "Creates and adds an element to the collection";
    }

    @Override
    public void execute() {
//        dataBase.add(person);
    }

}
