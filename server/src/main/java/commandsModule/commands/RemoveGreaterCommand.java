package commandsModule.commands;

import database.Database;
import defaultClasses.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class RemoveGreaterCommand implements BaseCommand, SingleArgumentCommand<Person> {
    private static final Logger logger = LogManager.getLogger("logger.RemoveGreaterCommand");
    private final String name = "remove_greater";
    private Person argument;
    private final Database dataBase;

    public RemoveGreaterCommand(Database dataBase) {
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
        return "Removes from the collection all elements greater than the specified";
    }

    @Override
    public void execute() throws IOException {
        try {
            dataBase.removeGreater(argument);
        } catch (Exception e) {
            dataBase.notifyCallerBack("Something went wrong during remove_greater {element} execution...");
            logger.warn("RemoveGreaterCommand was not executed");
            return;
        }
        logger.info("Executed RemoveGreaterCommand");
    }

}