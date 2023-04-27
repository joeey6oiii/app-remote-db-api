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
        dataBase.averageOfHeight();
        logger.info("AverageOfHeight is executed");
    }

}
