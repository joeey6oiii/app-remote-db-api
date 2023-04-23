package commandsModule.commands;

import commands.CommandDescription;
import dataBase.Database;

import java.io.IOException;

public class AverageOfHeightCommand implements BaseCommand {
    private final String name = "average_of_height";
    private final Database dataBase;

    public AverageOfHeightCommand(Database dataBase) {
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
        return "Outputs the average value of the \"height\" field of all elements in the database";
    }

    @Override
    public void execute() throws IOException {
        dataBase.averageOfHeight();
    }

}
