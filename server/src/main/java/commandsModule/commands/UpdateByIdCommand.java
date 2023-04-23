package commandsModule.commands;

import commands.CommandDescription;
import dataBase.DataBase;

import java.io.IOException;

public class UpdateByIdCommand implements BaseCommand, ParameterizedCommand {
    private final String name = "update_by_id";
    private String[] args;
    private final DataBase dataBase;

    public UpdateByIdCommand(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String[] getArguments() {
        return this.args;
    }

    @Override
    public void setArguments(String[] args) {
        this.args = args;
    }

    @Override
    public CommandDescription getCommandDescriptionObj() {
        return new CommandDescription(name);
    }

    @Override
    public String describe() {
        return "Allows to reassign values to objects in the database";
    }

    @Override
    public void execute() throws IOException {
        dataBase.update(Integer.parseInt(args[0]));
    }

}