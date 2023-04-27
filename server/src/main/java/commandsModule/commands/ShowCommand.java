package commandsModule.commands;

import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.ShowCommand");
    private final String name = "show";
    private final Database dataBase;

    public ShowCommand(Database dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String describe() {
        return "Prints to standard output all elements of the collection in string representation";
    }

    @Override
    public void execute() {
        try {
            dataBase.show();
        } catch (Exception e) {
            dataBase.notifyCallerBack("Something went wrong during show command execution...");
            logger.warn("ShowCommand was not executed");
            return;
        }
        logger.info("Executed ShowCommand");
    }

}
