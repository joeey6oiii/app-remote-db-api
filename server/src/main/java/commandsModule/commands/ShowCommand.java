package commandsModule.commands;

import commands.CommandDescription;
import dataBase.DataBase;

public class ShowCommand implements BaseCommand {
    private final String name = "show";
    private final DataBase dataBase;

    public ShowCommand(DataBase dataBase) {
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
        return "Prints to standard output all elements of the collection in string representation";
    }

    @Override
    public void execute() {
        dataBase.show();
    }

}
