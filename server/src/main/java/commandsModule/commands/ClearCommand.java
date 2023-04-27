package commandsModule.commands;

import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ClearCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.ClearCommand");
    private final String name = "clear";
    private final Database dataBase;

    public ClearCommand(Database dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String describe() {
        return "Clears the collection";
    }

    public void execute() throws IOException {
        try {
            dataBase.clear();
        } catch (Exception e) {
            dataBase.notifyCallerBack("Something went wrong during clear command execution...");
            logger.warn("Clear command was not executed");
            return;
        }
        logger.info("Executed ClearCommand");
    }

}
