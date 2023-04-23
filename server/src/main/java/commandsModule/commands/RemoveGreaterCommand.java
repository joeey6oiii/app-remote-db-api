package commandsModule.commands;

import commands.CommandDescription;
import dataBase.DataBase;
import defaultClasses.Person;
import generators.PersonGenerator;

import java.io.IOException;

public class RemoveGreaterCommand implements BaseCommand {
    private final String name = "remove_greater";
    private final DataBase dataBase;

    public RemoveGreaterCommand(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public CommandDescription getCommandDescriptionObj() {
        return new CommandDescription(name);
    }

    @Override
    public String describe() {
        return "Removes from the collection all elements greater than the specified";
    }

    @Override
    public void execute() throws IOException {
        PersonGenerator personGenerator = new PersonGenerator();
        Person person = personGenerator.generate();
        dataBase.removeGreater(person);
    }

}