package commandsModule.commands;

import java.io.IOException;

public interface BaseCommand {

    /**
     * A method that returns the name of the command.
     */

    String getName();

    /**
     * A method that returns the response of the command.
     */

    String getResponse();

    /**
     * A method that returns the description of the command.
     */

    String describe();

    void execute() throws IOException;

}