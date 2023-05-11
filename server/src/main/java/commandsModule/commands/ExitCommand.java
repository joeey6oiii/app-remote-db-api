package commandsModule.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ExitCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.ExitCommand");

    /**
     * A method that returns the name of the command.
     */

    @Override
    public String getName() {
        return "exit";
    }

    /**
     * A method that returns the response of the command.
     */

    @Override
    public String getResponse() {
        return "Standard \"exit\" command response";
    }

    /**
     * A method that returns the description of the command.
     */

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
