package commandsModule.commands;

import database.Database;

import java.io.IOException;

public class RemoveByIdCommand implements ParameterizedCommand {
    private final String name = "remove_by_id";
    private String[] args;
    private final Database dataBase;

    public RemoveByIdCommand(Database dataBase) {
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
    public void clearArguments() {
        this.args = new String[]{};
    }

    @Override
    public String describe() {
        return "Removes an element from the database by id";
    }

    @Override
    public void execute() throws IOException {
        dataBase.remove(Integer.parseInt(args[1]));
    }

}
