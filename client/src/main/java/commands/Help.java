package commands;

import java.io.IOException;
import java.util.Map;

/**
 * A class that implements the help command.
 */

public class Help extends BaseCommand {

    /**
     * When called, counts the length of each command name to find the longest one, then iterates through the collection of commands:
     * builds and prints an empty string with the length of the difference between the longest command name and current
     * command name plus four, then calls {@link BaseCommand#describe()} method of the current command in the collection.
     *
     * @throws IOException
     */

    @Override
    public void execute() throws IOException {
        String command = "";
        for (Map.Entry<String, BaseCommand> entry : CommandHandler.getMap().entrySet()) {
            if (entry.getKey().length() > command.length()) {
                command = entry.getKey();
            }
        }
        int commandLength = command.length();
        int emptyStringLength;
        for (Map.Entry<String, BaseCommand> entry : CommandHandler.getMap().entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            emptyStringLength = commandLength + 4 - entry.getKey().length();
            while (emptyStringLength != 0) {
                stringBuilder.append(" ");
                emptyStringLength -= 1;
            }
            System.out.println(entry.getKey() + stringBuilder + entry.getValue().describe());
        }
    }

    /**
     * A method that returns the description of the command.
     */

    @Override
    public String describe() {
        return "Displays information about console application commands";
    }
}
