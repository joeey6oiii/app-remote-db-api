package commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * A class that implements the history command.
 */

public class CommandHistory extends BaseCommand {

    /**
     * When called, gets the history from the {@link CommandHandler} and checks the size of the history list. If size
     * equals zero, outputs a <code>String</code>. Otherwise, creates an <code>ArrayList</code> and populates it with the key
     * values from the command map using the map values obtained from the command history, and then checks the size again:
     * if size is between zero and nine, outputs the whole history <code>ArrayList</code>, otherwise removes the first values in
     * the history <code>ArrayList</code> until its size is nine, and then outputs the history <code>ArrayList</code>.
     *
     * @throws IOException
     */

    @Override
    public void execute() throws IOException {
        if (CommandHandler.getHistory().size() == 0) {
            System.out.println("No command history yet");
            return;
        }
        ArrayList<String> history = new ArrayList<>();
        for (BaseCommand command : CommandHandler.getHistory()) {
            for (Map.Entry<String, BaseCommand> entry : CommandHandler.getMap().entrySet()) {
                if (command.equals(entry.getValue())) {
                    history.add(entry.getKey());
                }
            }
        }
        int i = 9;
        while (history.size() > i) {
            history.remove(history.size() - 1 - i);
        }
        System.out.println(history);
    }

    /**
     * A method that returns the description of the command.
     */

    @Override
    public String describe() {
        return "Outputs last input 9 commands (without arguments)";
    }
}
