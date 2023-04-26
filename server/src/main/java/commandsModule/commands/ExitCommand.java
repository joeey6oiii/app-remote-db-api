package commandsModule.commands;

import database.Database;

public class ExitCommand implements BaseCommand {
    private final String name = "exit";
    private final Database database;

    public ExitCommand(Database database) {
        this.database = database;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String describe() {
        return "Closes the program without saving";
    }

    @Override
    public void execute() {
        this.database.exit();
    }

}
