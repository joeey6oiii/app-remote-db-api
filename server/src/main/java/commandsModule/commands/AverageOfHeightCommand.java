package commandsModule.commands;

import database.Database;
import defaultClasses.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AverageOfHeightCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.AverageOfHeightCommand");
    private String response;

    @Override
    public String getName() {
        return "average_of_height";
    }

    @Override
    public String getResponse() {
        return this.response;
    }

    @Override
    public String describe() {
        return "Outputs the average value of the \"height\" field of all elements in the database";
    }

    @Override
    public void execute() throws IOException {
        Database database = Database.getInstance();
        if (database.getCollection().isEmpty()) {
            this.response = "Collection is empty, can not execute average_of_height";
        } else {
            double averageHeight = database.getCollection()
                    .stream().mapToInt(Person::getHeight).average().orElse(0);
            this.response = "The average \"height\" value is " + averageHeight;
        }
        logger.info("Executed AverageOfHeightCommand");
    }

}
