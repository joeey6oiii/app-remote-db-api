package commandsModule.commands;

import commandsModule.handler.CommandContext;
import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class HelpCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.HelpCommand");

    private final String name = "help";
    private final Database dataBase;
    private final CommandContext context;

    public HelpCommand(Database dataBase, CommandContext context) {
        this.dataBase = dataBase;
        this.context = context;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String describe() {
        return "Displays information about console application commands";
    }

    @Override
    public void execute() throws IOException {
        try {
            dataBase.help(context.getCommands());
        } catch (Exception e) {
            dataBase.notifyCallerBack("Something went wrong during help command execution...");
            logger.warn("HelpCommand was not executed");
            return;
        }
        logger.info("Executed HelpCommand");
    }

}
