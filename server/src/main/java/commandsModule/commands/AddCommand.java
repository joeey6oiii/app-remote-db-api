package commandsModule.commands;

import database.Database;
import defaultClasses.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class AddCommand implements BaseCommand, SingleArgumentCommand<Person> {
    private static final Logger logger = LogManager.getLogger("logger.add");
    private final String name = "add";
    private Person argument;
    private final Database dataBase;

    public AddCommand(Database dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
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
            dataBase.add(argument);
        } catch (Exception e) {
            dataBase.notifyCallerBack("Something went wrong during add {element} command execution...");
            logger.warn("AddCommand was not executed");
            return;
        }
        logger.info("Executed AddCommand");
    }

}
