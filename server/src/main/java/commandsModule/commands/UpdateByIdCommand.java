package commandsModule.commands;

import database.Database;
import defaultClasses.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class UpdateByIdCommand implements ParameterizedCommand, SingleArgumentCommand<Person> {
    private static final Logger logger = LogManager.getLogger("logger.UpdateByIdCommand");
    private final String name = "update_by_id";
    private String[] args;
    private Person argument;
    private final Database dataBase;

    public UpdateByIdCommand(Database dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String[] getArguments() {
        return this.args;
    }

    @Override
    public void setArguments(String[] args) {
        this.args = args;
    }

    @Override
    public void clearArguments() {
        this.args = new String[]{};
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
        return "Allows to reassign values to objects in the database";
    }

    @Override
    public void execute() throws IOException {
        try {
            dataBase.update(Integer.parseInt(args[1]), argument);
        } catch (Exception e) {
            dataBase.notifyCallerBack("Something went wrong during update_by_id {id} {element} command execution...");
            logger.warn("UpdateByIdCommand was not executed");
            return;
        }
        logger.info("Executed UpdateByIdCommand");

    }

}