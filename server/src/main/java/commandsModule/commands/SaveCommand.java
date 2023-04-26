package commandsModule.commands;

import database.Database;

public class SaveCommand implements BaseCommand {
    private final String name = "save";
    private final Database dataBase;

    public SaveCommand(Database dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String describe() {
        return "Saves the collection to a file";
    }

    @Override
    public void execute() {
        dataBase.save();
    }

}
