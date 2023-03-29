package commands;

import dataBase.DataBase;

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
     * @param obj link to the database which contains the collection
     * @throws IOException
     */

    @Override
    public void execute(DataBase obj) throws IOException {
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
            System.out.print(entry.getKey() + stringBuilder);
            entry.getValue().describe();
        }
    }

    /**
     * A method that outputs the description of the command.
     */

    @Override
    public void describe() {
        System.out.println("Displays information about console application commands");
    }
}
