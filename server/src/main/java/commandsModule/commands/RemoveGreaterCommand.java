package commandsModule.commands;

import database.Database;
import defaultClasses.Person;

import java.io.IOException;

public class RemoveGreaterCommand implements BaseCommand, SingleArgumentCommand<Person> {
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
        dataBase.removeGreater(argument);
    }

}