package commandsModule.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class HelpCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.HelpCommand");
    private String response;
    private final Map<String, BaseCommand> commands;

    public HelpCommand(Map<String, BaseCommand> commands) {
        this.commands = commands;
    }

    /**
     * A method that returns the name of the command.
     */

    @Override
    public String getName() {
        return "help";
    }

    /**
     * A method that returns the response of the command.
     */

    @Override
    public String getResponse() {
        return this.response;
    }

    /**
     * A method that returns the description of the command.
     */

    @Override
    public String describe() {
        return "Displays information about console application commands";
    }

    @Override
    public void execute() throws IOException {
        int commandLength = commands.keySet().stream().mapToInt(String::length).max().orElse(0);
        String formatString = "%-" + (commandLength + 4) + "s%s\n";
        StringBuilder builder;
        builder = new StringBuilder(commands.entrySet().stream()
                .map(entry -> String.format(formatString, entry.getKey() + " ", entry.getValue().describe()))
                .collect(Collectors.joining()));
        this.response = builder.substring(0, builder.length() - 1);
        logger.info("Executed HelpCommand");
    }

}
