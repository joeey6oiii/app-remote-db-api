package commands;

import java.util.Scanner;

/**
 * Class for entering commands from the terminal
 *
 * @author Dmitrii Chebanenko
 */
public class CommandManager {
    /**
     * Method to Start CommandManager
     */
    public void startWorking() {
        System.out.println("CommandManager started");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String str = scanner.nextLine();
            CommandHandler.handleCommand(str);
        }
    }

}
