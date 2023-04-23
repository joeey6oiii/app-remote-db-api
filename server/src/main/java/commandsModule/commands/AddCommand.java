package commandsModule.commands;

import commands.CommandDescription;
import dataBase.DataBase;
import dataBase.Loader;
import defaultClasses.Person;
import generators.PersonGenerator;

import java.util.ArrayList;
import java.util.Collections;

public class AddCommand implements BaseCommand {
    private final String name = "add";
    private final DataBase dataBase;

    public AddCommand(DataBase dataBase) {
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
        return "Creates and adds an element to the collection";
    }

    @Override
    public void execute() {
        new Loader().load(dataBase, Collections.singletonList(new PersonGenerator().generate()));
    }

}
