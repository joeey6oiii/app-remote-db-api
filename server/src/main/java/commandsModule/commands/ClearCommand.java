package commandsModule.commands;

import commands.CommandDescription;
import dataBase.DataBase;

public class ClearCommand implements BaseCommand {
    private final String name = "clear";
    private final DataBase dataBase;

    public ClearCommand(DataBase dataBase) {
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
