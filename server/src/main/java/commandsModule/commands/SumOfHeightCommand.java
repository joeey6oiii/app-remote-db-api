package commandsModule.commands;

import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class SumOfHeightCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.SumOfHeightCommand");
    private final String name = "sum_of_height";
    private final Database dataBase;

    public SumOfHeightCommand(Database dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String describe() {
        return "Outputs the sum of the \"height\" values of all elements in the database";
    }

    @Override
    public void execute() throws IOException {
        try {
            dataBase.sumOfHeight();
        } catch (Exception e) {
            dataBase.notifyCallerBack("Something went wrong during sum_of_height command execution...");
            logger.warn("SumOfHeightCommand was not executed");
            return;
        }
        logger.info("Executed SumOfHeightCommand");

    }

}
