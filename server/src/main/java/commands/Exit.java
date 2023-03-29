package commands;

import dataBase.DataBase;

import java.util.Scanner;

/**
 * Class for implementing the exit command - exits the program without saving to a file
 *
 * @author Dmitrii Chebanenko
 */
public class Exit extends BaseCommand {
    /**
     * Method that interrupts the program without saving to a file
     *
     * @param obj - link to the database containing the collection
     */
    public void execute(DataBase obj) {
        System.out.println("Are you sure you want to end the program?");
        System.out.println("Enter [Y/N]");
        Scanner scanner = new Scanner(System.in);
        String str = "";
        while (true) {
            try {
                System.out.print("$ ");
                str = scanner.next();
            } catch (Exception e) {
                System.out.println("Enter [y/n]");
            }
            if (str.equalsIgnoreCase("Y"))
                System.exit(0);
            if (str.equalsIgnoreCase("N"))
                return;
            System.out.println("Enter [y/n]");
        }
    }

    /**
     * Method that displays the description of the command
     */
    public void describe() {
        System.out.println("Closes the program without saving");
    }
}
