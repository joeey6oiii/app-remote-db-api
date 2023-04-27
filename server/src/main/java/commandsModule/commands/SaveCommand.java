package commandsModule.commands;

import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class SaveCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.SaveCommand");
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
        return "Saves the collection to a file. Can be executed only by server";
    }

    @Override
    public void execute() throws IOException {
        try {
            dataBase.save();
        } catch (Exception e) {
            logger.warn("SaveCommand was not executed");
            return;
        }
        logger.info("Executed SaveCommand");
    }

}
