package commandsModule.commands;

import database.Database;
import database.IDService;
import defaultClasses.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Class for implementing the add command.
 */

public class AddCommand implements BaseCommand, SingleArgumentCommand<Person> {
    private static final Logger logger = LogManager.getLogger("logger.AddCommand");
    private String response;
    private Person argument;

    /**
     * A method that returns the name of the command.
     */

    @Override
    public String getName() {
        return "add";
    }

    /**
     * A method that returns the response of the command.
     */

    @Override
    public String getResponse() {
        return this.response;
    }

    @Override
    public Person getSingleArgument() {
        return this.argument;
    }

    @Override
    public void setSingleArgument(Person argument) {
        this.argument = argument;
    }

    /**
     * Method that returns the description of the command.
     */

    @Override
    public String describe() {
        return "Adds element to collection";
    }

    /**
     * When called, adds received Person object to the collection.
     */

    @Override
    public void execute() throws IOException {
        Database.getInstance().add(IDService.recalculateId(argument));
        this.response = "Element was added";
        logger.info("Executed AddCommand");
    }
}
