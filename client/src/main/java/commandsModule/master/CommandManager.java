package commandsModule.master;

import clientModules.connection.ConnectionModule;
import clientModules.response.receivers.ArgumentCommandResultReceiver;
import clientModules.response.receivers.CommandResultReceiver;
import clientModules.response.receivers.CommandReceiver;
import commands.CommandDescription;
import commands.CommandType;
import exceptions.IllegalManagerArgumentException;

import java.util.HashMap;
import java.util.Optional;

public class CommandManager {
    private final HashMap<CommandType, CommandReceiver> map;

    {
        this.map = new HashMap<>();

        map.put(CommandType.ARGUMENTLESS, new CommandResultReceiver());
        map.put(CommandType.PERSON_SINGLE_ARGUMENT, new ArgumentCommandResultReceiver());
    }

    public void manageCommand(CommandDescription cmd, String[] arr, ConnectionModule module) {
        try {
            Optional.ofNullable(map.get(cmd.getType())).orElseThrow(() ->
                    new IllegalManagerArgumentException("CommandManager contains illegal argument")).receiveCommand(cmd, arr, module);
        } catch (IllegalManagerArgumentException e) {
            e.printStackTrace();
        }
    }
}
