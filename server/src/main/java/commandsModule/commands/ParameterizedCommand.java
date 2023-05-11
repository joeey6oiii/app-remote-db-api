package commandsModule.commands;

/**
 * Abstract interface for all commands-implementers with arguments.
 */

public interface ParameterizedCommand extends BaseCommand {

    /**
     * A method that sets arguments to the command.
     *
     * @param args arguments of the command
     */

    void setArguments(String[] args);

    /**
     * A method that returns arguments of the command.
     */

    String[] getArguments();

}
