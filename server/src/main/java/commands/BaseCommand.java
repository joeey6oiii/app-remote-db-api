package commands;

import java.io.IOException;

/**
 * Abstraction class for all commands.
 *
 * @author Dmitrii Chebanenko
 */

public abstract class BaseCommand {
    private String name;

    private String getName() {
        return this.name;
    }

    /**
     * Parameter holding the command argument (if any).
     */

    private String parameter;

    /**
     * Base constructor, sets the argument to null.
     */

    public BaseCommand() {
        parameter = null;
    }

    /**
     * Constructor that specifies an argument for the command.
     *
     * @param obj - аргумент для команды
     */

    public BaseCommand(String obj) {
        parameter = obj;
    }

    /**
     * Method for getting value {@link BaseCommand#parameter}.
     *
     * @return return command argument
     */

    public String getParameter() {
        return parameter;
    }

    /**
     * Method for defining command argument {@link BaseCommand#parameter}.
     *
     * @param parameter - command argument
     */

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Abstract command call method.
     *
     * @throws IOException
     */

    public abstract void execute() throws IOException;

    /**
     * Abstract method, when overridden returns a description of the command.
     */

    public abstract String describe();

    public CommandDescription getCommandDescriptionObj() {
        return new CommandDescription(name);
    }
}