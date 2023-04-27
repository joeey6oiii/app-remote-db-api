package commandsModule.commands;

import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public void execute() {
        dataBase.clear();
        logger.info("ClearCommand is executed");
    }

}
