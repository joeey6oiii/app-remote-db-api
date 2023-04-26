package commandsModule.commands;

import commandsModule.handler.CommandContext;
import database.Database;

import java.io.IOException;

public class HistoryCommand implements BaseCommand {
    private final String name = "history";
    private final Database dataBase;
    private final CommandContext context;

    public HistoryCommand(Database dataBase, CommandContext context) {
        this.dataBase = dataBase;
        this.context = context;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String describe() {
        return "Outputs last input 9 commands without arguments";
    }

    @Override
    public void execute() throws IOException {
        dataBase.history(context.getCommands(), context.getHistory());
    }

}
