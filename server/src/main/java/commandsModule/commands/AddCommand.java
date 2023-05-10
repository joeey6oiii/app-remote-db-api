package commandsModule.commands;

import database.Database;
import database.IDService;
import defaultClasses.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AddCommand implements BaseCommand, SingleArgumentCommand<Person> {
    private static final Logger logger = LogManager.getLogger("logger.AddCommand");
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
        return "Adds element to collection";
    }

    @Override
    public void execute() throws IOException {
        Database.getInstance().add(IDService.recalculateId(argument));
        this.response = "Element was added";
        logger.info("Executed AddCommand");
    }
}
