package commandsModule.commands;

import database.Database;
import defaultClasses.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class UpdateByIdCommand implements ParameterizedCommand, SingleArgumentCommand<Person> {

    private static final Logger logger = LogManager.getLogger("logger.UpdateByIdCommand");

    private String response;
    private String[] args;
    private Person argument;

    @Override
    public String getName() {
        return "update_by_id";
    }

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
        Database database = Database.getInstance();
        try {
            int id = Integer.parseInt(args[1]);
            Optional<Person> optionalPerson = database.getCollection()
                    .stream().filter(p -> Objects.equals(p.getId(), id)).findFirst();
            if (optionalPerson.isPresent()) {
                Person existingPerson = optionalPerson.get();
                database.getCollection().remove(existingPerson);
                argument.setId(id);
                database.add(argument);
                this.response ="Updated element with id " + id;
            } else {
                this.response = "No element matches id " + id;
            }
            logger.info("Executed UpdateByIdCommand");
        } catch (Exception e) {
            this.response = "Something went wrong during update_by_id {id} {element} command execution...";
            logger.warn("UpdateByIdCommand was not executed");
        }
    }
}