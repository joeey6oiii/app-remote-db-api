package commandsModule.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ExitCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.ExitCommand");

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getResponse() {
        return "Standard \"exit\" command response";
    }

    @Override
    public String describe() {
        return "Closes the program without saving";
    }

    @Override
    public void execute() throws IOException {
        logger.info("Client disconnects. Saving database data to a file...");
        new SaveCommand().execute();
    }

}
