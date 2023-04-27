package commandsModule.master;

import clientModules.connection.ConnectionModule;
import commands.CommandDescription;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandHandler {

    private final Map<String, CommandDescription> commands;

    private final Scanner scanner;

    public CommandHandler(List<CommandDescription> commands, Scanner scanner) {
        this.commands = commands.stream().collect(Collectors.toMap(CommandDescription::getCommandName, Function.identity()));
        this.scanner = scanner;
    }

    public void run(ConnectionModule module) {
        String input;
        while(true) {
            System.out.print("$ ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) { continue;}
            String[] arr = input.split(" ");
            CommandDescription cmd = commands.get(arr[0]);
            if (cmd != null) {
                new CommandManager().manageCommand(cmd, arr, module);
            }
        }
    }

}
