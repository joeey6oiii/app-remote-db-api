package commandsModule.commands;

import commands.CommandDescription;
import database.Database;

import java.io.IOException;

public class RemoveGreaterCommand implements BaseCommand {
    private final String name = "remove_greater";
    private final Database dataBase;

    public RemoveGreaterCommand(Database dataBase) {
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
        return "Removes from the collection all elements greater than the specified";
    }

    @Override
    public void execute() throws IOException {
//        dataBase.removeGreater(person);
    }

}