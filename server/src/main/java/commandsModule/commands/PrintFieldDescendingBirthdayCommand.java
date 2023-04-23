package commandsModule.commands;

import commands.CommandDescription;
import dataBase.DataBase;

import java.io.IOException;

public class PrintFieldDescendingBirthdayCommand implements BaseCommand {
    private final String name = "print_field_descending_birthday";
    private final DataBase dataBase;

    public PrintFieldDescendingBirthdayCommand(DataBase dataBase) {
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
        return "Outputs the \"birthday\" values of all elements in the database in descending order";
    }

    @Override
    public void execute() throws IOException {
        dataBase.printFieldDescendingBirthday();
    }

}
