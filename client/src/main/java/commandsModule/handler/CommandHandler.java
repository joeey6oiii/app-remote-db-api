package commandsModule.handler;

import clientModules.connection.DataTransferConnectionModule;
import commands.CommandDescription;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandHandler {
    private static Map<String, CommandDescription> commands;
    private final CommandManager manager;
    private final DataTransferConnectionModule module;
    private final Scanner scanner;

    public CommandHandler(Map<String, CommandDescription> commands, Scanner scanner, DataTransferConnectionModule module) {
        CommandHandler.commands = commands;
        this.scanner = scanner;
        this.module = module;
        manager = new CommandManager();
    }

    public CommandHandler(List<CommandDescription> commands, Scanner scanner, DataTransferConnectionModule module) {
        CommandHandler.commands = commands.stream().collect(Collectors.toMap(CommandDescription::getCommandName, Function.identity()));
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

    public void startHandling() {
        String input;
        while (true) {
            System.out.print("$ ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) { continue; }
            String[] arr = input.toLowerCase().split(" ");
            CommandDescription cmd = commands.get(arr[0]);
            if (cmd != null) {
                manager.manageCommand(cmd, arr, module);
            } else {
                System.out.println("Not Recognized as an Internal or External Command");
            }
        }
    }

}
