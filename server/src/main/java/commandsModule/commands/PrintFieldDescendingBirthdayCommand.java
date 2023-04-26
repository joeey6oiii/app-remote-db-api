package commandsModule.commands;

import database.Database;

import java.io.IOException;

public class PrintFieldDescendingBirthdayCommand implements BaseCommand {
    private final String name = "print_field_descending_birthday";
    private final Database dataBase;

    public PrintFieldDescendingBirthdayCommand(Database dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
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
