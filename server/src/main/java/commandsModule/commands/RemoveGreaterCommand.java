package commandsModule.commands;

import comparators.HeightComparator;
import database.Database;
import defaultClasses.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class RemoveGreaterCommand implements BaseCommand, SingleArgumentCommand<Person> {
    private static final Logger logger = LogManager.getLogger("logger.RemoveGreaterCommand");
    private String response;
    private Person argument;

    /**
     * A method that returns the name of the command.
     */

    @Override
    public String getName() {
        return "remove_greater";
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
     * A method that returns the description of the command.
     */

    @Override
    public String describe() {
        return "Removes from the collection all elements greater than the specified";
    }

    @Override
    public void execute() throws IOException {
        Database database = Database.getInstance();
        if (database.getCollection().isEmpty()) {
            this.response = "Collection is empty, there is nothing to remove";
        } else {
            database.getCollection().removeIf(p -> new HeightComparator().compare(p, argument) > 0);
            this.response = "Removed elements whose height parameter is greater than " + argument.getHeight();
        }
        logger.info("Executed RemoveGreaterCommand");
    }

}