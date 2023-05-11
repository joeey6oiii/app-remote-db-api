package commandsModule.handler;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.response.receivers.*;
import commands.CommandDescription;
import commands.CommandType;
import exceptions.IllegalManagerArgumentException;

import java.util.HashMap;
import java.util.Optional;

/**
 * A class that manages simplified commands.
 */

public class CommandManager {
    private final HashMap<CommandType, CommandReceiver> map;

    {
        this.map = new HashMap<>();

        map.put(CommandType.ARGUMENTLESS, new ExecutionResultReceiver());
        map.put(CommandType.PERSON_SINGLE_ARGUMENT, new PersonCommandResultReceiver());
        map.put(CommandType.EXIT, new ExitCommandReceiver());
        map.put(CommandType.EXECUTE_SCRIPT, new ScriptCommandReceiver());
    }

    /**
     * Finds a matching simplified command with a received simplified command and manages it using the
     * {@link CommandReceiver#receiveCommand(CommandDescription, String[], DataTransferConnectionModule)} method.
     * 
     * @param cmd simplified command
     * @param args simplified command arguments
     * @param module client core
     */

    public void manageCommand(CommandDescription cmd, String[] args, DataTransferConnectionModule module) {
        try {
            Optional.ofNullable(map.get(cmd.getType())).orElseThrow(() ->
                    new IllegalManagerArgumentException("CommandManager contains illegal argument")).receiveCommand(cmd, args, module);
        } catch (IllegalManagerArgumentException e) {
            System.out.println("Failed to manage response");
        }
    }

}
