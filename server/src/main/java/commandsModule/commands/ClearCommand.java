package commandsModule.commands;

import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ClearCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.ClearCommand");
    private String response;

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getResponse() {
        return this.response;
    }

    @Override
    public String describe() {
        return "Clears the collection";
    }

    @Override
    public void execute() throws IOException {
        Database database = Database.getInstance();
        try {
            if (database.getCollection().isEmpty()) {
                this.response = "Collection is empty, there is nothing to clear";
            } else {
                database.clear();
                this.response = "Cleared the collection";
            }
            logger.info("Executed ClearCommand");
        } catch (Exception e) {
            this.response = "Something went wrong during clear command execution...";
            logger.warn("Clear command was not executed", e);
        }
    }

}
