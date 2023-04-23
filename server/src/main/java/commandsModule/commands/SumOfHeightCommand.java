package commandsModule.commands;

import commands.CommandDescription;
import dataBase.DataBase;

import java.io.IOException;

public class SumOfHeightCommand implements BaseCommand {
    private final String name = "sum_of_height";
    private final DataBase dataBase;

    public SumOfHeightCommand(DataBase dataBase) {
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
        return "Outputs the sum of the \"height\" values of all elements in the database";
    }

    @Override
    public void execute() throws IOException {
        dataBase.sumOfHeight();
    }

}
