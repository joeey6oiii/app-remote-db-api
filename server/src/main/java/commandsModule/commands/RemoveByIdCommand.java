package commandsModule.commands;

import commands.CommandDescription;
import dataBase.DataBase;

import java.io.IOException;

public class RemoveByIdCommand implements BaseCommand, ParameterizedCommand {
    private final String name = "remove_by_id";
    private String parameter;
    private final DataBase dataBase;

    public RemoveByIdCommand(DataBase dataBase) {
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
        return "Removes an element from the database by id";
    }

    @Override
    public void execute() throws IOException {
        dataBase.remove(Integer.parseInt(parameter));
    }

}
