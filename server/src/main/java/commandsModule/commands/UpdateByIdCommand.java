package commandsModule.commands;

import commands.CommandDescription;
import dataBase.DataBase;

import java.io.IOException;

public class UpdateByIdCommand implements BaseCommand, ParameterizedCommand {
    private final String name = "update_by_id";
    private String parameter;
    private final DataBase dataBase;

    public UpdateByIdCommand(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getParameter() {
        return this.parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
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
        dataBase.update(Integer.parseInt(parameter));
    }

}