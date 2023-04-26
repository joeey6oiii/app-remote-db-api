package commandsModule.commands;

import database.Database;

public class ShowCommand implements BaseCommand {
    private final String name = "show";
    private final Database dataBase;

    public ShowCommand(Database dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String describe() {
        return "Prints to standard output all elements of the collection in string representation";
    }

    @Override
    public void execute() {
        dataBase.show();
    }

}
