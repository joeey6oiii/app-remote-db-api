package commandsModule.commands;

import commandsModule.handler.CommandContext;
import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class HistoryCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.HistoryCommand");

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
        try {
            dataBase.history(context.getCommands(), context.getHistory());
        } catch (Exception e) {
            dataBase.notifyCallerBack("Something went wrong during history command execution...");
            logger.warn("HistoryCommand was not executed");
            return;
        }
        logger.info("Executed HistoryCommand");
    }

}
