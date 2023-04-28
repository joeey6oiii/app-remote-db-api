package commandsModule.commands;

import fileService.FileService;
import database.Database;
import main.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements BaseCommand {

    private static final Logger logger = LogManager.getLogger("logger.SaveCommand");

    private String response;

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getResponse() {
        return this.response;
    }

    @Override
    public String describe() {
        return "Saves the collection to a file. Can be executed only by server";
    }

    @Override
    public void execute() throws IOException {
        Database database = Database.getInstance();
        try {
            File file = new File("server\\src\\main\\resources\\Person.yaml");
            FileService fileService = new FileService();
            if (!file.exists()) {
                fileService.createFile(file);
            }
            fileService.writeObjectToFile(file, database.getCollection());
            this.response = "Saved collection";
            logger.info("Executed SaveCommand");
        } catch (Exception e) {
            this.response = "Failed to save collection";
            logger.warn("SaveCommand was not executed");
        }
    }
}
