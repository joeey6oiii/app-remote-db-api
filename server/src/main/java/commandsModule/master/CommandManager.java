package commandsModule.master;

import commandsModule.master.CommandHandler;

import java.util.Scanner;

/**
 * Class for entering commands from the terminal
 *
 * @author Dmitrii Chebanenko
 */
public class CommandManager {
    private final CommandHandler handler;

    public CommandManager(CommandHandler handler) {
        this.handler = handler;
    }

    public void startWorking() {
        System.out.println("CommandManager started");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String str = scanner.nextLine();
            handler.handleCommand(str);
        }
    }

}
