package commandsModule.commands;

import database.Database;
import defaultClasses.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class RemoveLowerCommand implements BaseCommand, SingleArgumentCommand<Person> {
    private static final Logger logger = LogManager.getLogger("logger.RemoveLowerCommand");
    private final String name = "remove_lower";
    private Person argument;
    private final Database dataBase;

    public RemoveLowerCommand(Database dataBase) {
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
        return "Removes from the collection all elements lower than the specified";
    }

    @Override
    public void execute() throws IOException {
        try {
            dataBase.removeLower(argument);
        } catch (Exception e) {
            dataBase.notifyCallerBack("Something went wrong during remove_lower {element} command execution...");
            logger.warn("RemoveLowerCommand was not executed");
            return;
        }
        logger.info("Executed RemoveLowerCommand");
    }

}
