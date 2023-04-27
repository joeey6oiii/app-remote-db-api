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
        dataBase.show();
        logger.info("ShowCommand is executed");
    }

}
