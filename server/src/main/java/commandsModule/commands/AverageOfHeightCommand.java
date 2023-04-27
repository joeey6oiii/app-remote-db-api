package commandsModule.commands;

import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AverageOfHeightCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.AverageOfHeightCommand");
    private final String name = "average_of_height";
    private final Database dataBase;

    public AverageOfHeightCommand(Database dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String describe() {
        return "Outputs the average value of the \"height\" field of all elements in the database";
    }

    @Override
    public void execute() throws IOException {
        try {
            dataBase.averageOfHeight();
        } catch (Exception e) {
            dataBase.notifyCallerBack("Something went wrong during average_of_height command execution...");
            logger.warn("AverageOfHeightCommand was not executed");
            return;
        }
        logger.info("Executed AverageOfHeightCommand");
    }

}
