package commandsModule.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ExecuteScriptCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.ExecuteScriptCommand");

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getResponse() {
        return "Server is waiting to receive commands...";
    }

    @Override
    public String describe() {
        return "Reads and executes a script from the specified file";
    }

    @Override
    public void execute() throws IOException {
        logger.info("Executed ExecuteScriptCommand. Waiting to receive commands from the Client...");
    }

}
