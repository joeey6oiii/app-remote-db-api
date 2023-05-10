package commandsModule.commands;

import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

public class ShowCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.ShowCommand");
    private String response;

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getResponse() {
        return this.response;
    }

    @Override
    public String describe() {
        return "Prints to standard output all elements of the collection in string representation";
    }

    @Override
    public void execute() {
        Database database = Database.getInstance();
        try {
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
        } catch (Exception e) {
            this.response = "Something went wrong during show command execution...";
            logger.warn("ShowCommand was not executed", e);
        }
    }

}
