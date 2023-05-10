package commandsModule.commands;

import fileService.FileService;
import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.SaveCommand");
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getResponse() {
        return "Executed only by server";
    }

    @Override
    public String describe() {
        return "Saves the collection to a file. Can be executed only by server";
    }

    @Override
    public void execute() throws IOException {
        Database database = Database.getInstance();
        File file = new File("server\\src\\main\\resources\\Person.yaml");
        FileService fileService = new FileService();
        try {
            if (!file.exists()) {
                fileService.createFile(file);
            }
            fileService.writeObjectToFile(file, database.getCollection());
            logger.info("Saved collection to a file");
        } catch (Exception e) {
            logger.fatal("Unexpected event: Failed to save data to file", e);
        }
    }
}
