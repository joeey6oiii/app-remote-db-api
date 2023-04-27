package commandsModule.commands;

import database.Database;
import defaultClasses.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class AddCommand implements BaseCommand, SingleArgumentCommand<Person> {

    private static final Logger logger = LogManager.getLogger("logger.add");

    private String response;
    private Person argument;

    @Override
    public String getName() {
        return "add";
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
        return "Creates and adds an element to the collection";
    }

    @Override
    public void execute() throws IOException {
        try {
            Database.getInstance().add(argument);
            this.response = "Element was added";
            logger.info("Executed AddCommand");
        } catch (Exception e) {
            this.response = "Something went wrong during add {element} command execution...";
            logger.warn("AddCommand was not executed");
        }
    }
}
