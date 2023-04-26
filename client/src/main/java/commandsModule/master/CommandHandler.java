package commandsModule.master;

import clientModules.connection.ConnectionModule;
import commands.CommandDescription;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandHandler {

    private final List<CommandDescription> commands;

    private final Scanner scanner;

    public CommandHandler(List<CommandDescription> commands, Scanner scanner) {
        this.commands = commands;
        this.scanner = scanner;
    }

    public void run(CommandManager manager, ConnectionModule module) {
        String s;
        while(true) {
            s = scanner.nextLine();
            if (s.isEmpty()) { continue;}
            String[] arr = s.split(" ");
            for (CommandDescription cmd : commands) {
                if (cmd.getCommandName().equals(arr[0])) {
                    manager.manageCommand(cmd, arr, module);
                }
            }
        }
    }

}
