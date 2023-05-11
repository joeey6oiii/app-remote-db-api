package commandsModule.commands;

import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

public class ShowCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.ShowCommand");
    private String response;

    /**
     * A method that returns the name of the command.
     */

    @Override
    public String getName() {
        return "show";
    }

    /**
     * A method that returns the response of the command.
     */

    @Override
    public String getResponse() {
        return this.response;
    }

    /**
     * A method that returns the description of the command.
     */

    @Override
    public String describe() {
        return "Prints to standard output all elements of the collection in string representation";
    }

    @Override
    public void execute() {
        Database database = Database.getInstance();
        if (database.getCollection().isEmpty()) {
            this.response = "Collection is empty, there is nothing to show";
        } else {
            StringBuilder builder;
            builder = new StringBuilder(database.getCollection()
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("\n")));
            this.response = builder.substring(0, builder.length() - 1);
        }
        logger.info("Executed ShowCommand");
    }

}
