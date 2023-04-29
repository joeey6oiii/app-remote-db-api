package commandsModule.commands;

import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InfoCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.InfoCommand");
    private String response;

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getResponse() {
        return this.response;
    }

    @Override
    public String describe() {
        return "Prints information about the collection to the standard" +
                " output stream (type, initialization date, number of elements, etc.)";
    }

    @Override
    public void execute() {
        Database database = Database.getInstance();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("Type: ").append(database.getCollection().getClass()).append("\nLength: ")
                    .append(database.getCollection().size()).append("\nInitialization Time: ")
                    .append(database.getInitializationTime());
            this.response = new String(builder);
            logger.info("Executed InfoCommand");
        } catch (Exception e) {
            this.response = "Something went wrong during info command execution...";
            logger.warn("InfoCommand was not executed");
        }
    }
}
