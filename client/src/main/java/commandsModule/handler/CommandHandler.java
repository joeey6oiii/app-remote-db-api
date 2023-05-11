package commandsModule.handler;

import clientModules.connection.DataTransferConnectionModule;
import commands.CommandDescription;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandHandler {
    private static Map<String, CommandDescription> commands;
    private static Map<CommandDescription, String[]> missedCommands;
    private final CommandManager manager;
    private final DataTransferConnectionModule module;
    private final Scanner scanner;

    public CommandHandler(Map<String, CommandDescription> commands, Scanner scanner, DataTransferConnectionModule module) {
        CommandHandler.commands = commands;
        missedCommands = new LinkedHashMap<>();
        this.scanner = scanner;
        this.module = module;
        manager = new CommandManager();
    }

    public CommandHandler(List<CommandDescription> commands, Scanner scanner, DataTransferConnectionModule module) {
        CommandHandler.commands = commands.stream().collect(Collectors.toMap(CommandDescription::getCommandName, Function.identity()));
        missedCommands = new LinkedHashMap<>();
        this.scanner = scanner;
        this.module = module;
        manager = new CommandManager();
    }

    public static CommandDescription getCommandByName(String name) {
        if (commands != null) {
            return commands.get(name);
        }
        return null;
    }

    public static Map<CommandDescription, String[]> getMissedCommands() {
        if (missedCommands != null) {
            return missedCommands;
        }
        missedCommands = new LinkedHashMap<>();
        return missedCommands;
    }

    public void startHandling() {
        String input;
        while (true) {
            if (!missedCommands.isEmpty()) {
                System.out.println("Trying to send commands from missed commands collection...");
                Map<CommandDescription, String[]> copyMissedCommands = new LinkedHashMap<>(missedCommands);
                copyMissedCommands.forEach((key, value) -> manager.manageCommand(key, value, module));
            }
            if (!missedCommands.isEmpty()) {
                System.out.println("Server failed to execute some commands " +
                        "(perhaps the server is or was unavailable). Returning to the console input");
            }

            System.out.print("$ ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) { continue; }
            String[] arr = input.toLowerCase().split(" ");
            CommandDescription cmd = commands.get(arr[0]);
            if (cmd == null) {
                System.out.println("Not Recognized as an Internal or External Command");
                continue;
            }
            if (!missedCommands.isEmpty()) {
                missedCommands.put(cmd, arr);
                System.out.println("Added command to the end of the missed commands collection due to its not emptiness");
            } else {
                manager.manageCommand(cmd, arr, module);
            }
        }
    }

}
