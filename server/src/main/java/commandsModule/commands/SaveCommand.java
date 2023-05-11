package commandsModule.commands;

import fileService.FileService;
import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.SaveCommand");

    /**
     * A method that returns the name of the command.
     */

    @Override
    public String getName() {
        return "save";
    }

    /**
     * A method that returns the response of the command.
     */

    @Override
    public String getResponse() {
        return "Executed only by server";
    }

    /**
     * A method that returns the description of the command.
     */

    @Override
    public String describe() {
        return "Saves the collection to a file. Can be executed only by server";
    }

    @Override
    public void execute() throws IOException {
        Database database = Database.getInstance();
        File file = new File("server\\src\\main\\resources\\Person.yaml");
        FileService fileService = new FileService();
        if (!file.exists()) {
            fileService.createFile(file);
        }
        fileService.writeObjectToFile(file, database.getCollection());
        logger.info("Saved collection to a file");
    }
}
