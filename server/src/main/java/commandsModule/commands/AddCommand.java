package commandsModule.commands;

import commands.CommandDescription;
import commands.CommandType;
import database.Database;
import defaultClasses.Person;

public class AddCommand implements BaseCommand, SingleArgumentCommand<Person> {
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
    public void execute() {
        dataBase.add(argument);
    }

}
