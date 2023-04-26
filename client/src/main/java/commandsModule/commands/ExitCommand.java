package commandsModule.commands;

import java.util.Scanner;

public class ExitCommand implements BaseCommand {
    private final String name = "exit";

    public ExitCommand() {}

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String describe() {
        return "Closes the program without saving";
    }

    @Override
    public void execute() {
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

}
