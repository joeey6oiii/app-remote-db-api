package commandsModule.commands;

import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class RemoveByIdCommand implements ParameterizedCommand {
    private static final Logger logger = LogManager.getLogger("logger.RemoveByIdCommand");
    private String response;
    private String[] args;

    /**
     * A method that returns the name of the command.
     */

    @Override
    public String getName() {
        return "remove_by_id";
    }

    /**
     * A method that returns the response of the command.
     */

    @Override
    public String getResponse() {
        return this.response;
    }

    @Override
    public String[] getArguments() {
        return this.args;
    }

    @Override
    public void setArguments(String[] args) {
        this.args = args;
    }

    /**
     * A method that returns the description of the command.
     */

    @Override
    public String describe() {
        return "Removes an element from the database by id";
    }

    @Override
    public void execute() throws IOException {
        Database database = Database.getInstance();
        int id = Integer.parseInt(args[1]);
        if (database.getCollection().isEmpty()) {
            this.response = "Collection is empty, there is nothing to remove";
        } else {
            if (database.remove(id)) {
                this.response = "Removed element with id " + id;
            } else {
                this.response = "No element matches id " + id;
            }
        }
        logger.info("Executed RemoveByIdCommand");
    }
}
