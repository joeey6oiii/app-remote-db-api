package commandsModule.commands;

import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InfoCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.InfoCommand");

    private final String name = "info";
    private final Database dataBase;

    public InfoCommand(Database dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String describe() {
        return "Prints information about the collection to the standard" +
                " output stream (type, initialization date, number of elements, etc.)";
    }

    @Override
    public void execute() {
        try {
            dataBase.info();
        } catch (Exception e) {
            dataBase.notifyCallerBack("Something went wrong during info command execution...");
            logger.warn("InfoCommand was not executed");
            return;
        }
        logger.info("Executed InfoCommand");
    }

}
