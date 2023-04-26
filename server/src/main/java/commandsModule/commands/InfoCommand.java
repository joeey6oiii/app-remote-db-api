package commandsModule.commands;

import database.Database;

public class InfoCommand implements BaseCommand {
    private final String name = "info";
    private final Database dataBase;

    public InfoCommand(Database dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String describe() {
        return "Prints information about the collection to the standard" +
                " output stream (type, initialization date, number of elements, etc.)";
    }

    @Override
    public void execute() {
        dataBase.info();
    }

}
