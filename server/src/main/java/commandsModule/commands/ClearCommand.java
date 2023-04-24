package commandsModule.commands;

import commands.CommandDescription;
import database.Database;

public class ClearCommand implements BaseCommand {
    private final String name = "clear";
    private final Database dataBase;

    public ClearCommand(Database dataBase) {
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

    public String describe() {
        return "Clears the collection";
    }

    public void execute() {
        dataBase.clear();
    }

}
