package commandsModule.commands;

import comparators.HeightComparator;
import database.Database;
import defaultClasses.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class RemoveLowerCommand implements BaseCommand, SingleArgumentCommand<Person> {
    private static final Logger logger = LogManager.getLogger("logger.RemoveLowerCommand");
    private String response;
    private Person argument;

    @Override
    public String getName() {
        return "remove_lower";
    }

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

    @Override
    public String describe() {
        return "Removes from the collection all elements lower than the specified";
    }

    @Override
    public void execute() throws IOException {
        Database database = Database.getInstance();
        try {
            if (database.getCollection().isEmpty()) {
                this.response = "Collection is empty, there is nothing to remove";
            } else {
                database.getCollection().removeIf(p -> new HeightComparator().compare(p, argument) < 0);
                this.response = "Removed elements whose height parameter is lower than " + argument.getHeight();
            }
            logger.info("Executed RemoveLowerCommand");
        } catch (Exception e) {
            this.response = "Something went wrong during remove_lower {element} command execution...";
            logger.warn("RemoveLowerCommand was not executed");
        }
    }

}
