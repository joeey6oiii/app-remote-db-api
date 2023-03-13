package helpFun;

import java.util.Scanner;

/**
 * A class with help decision method.
 */

public class Decision {

    /**
     * @param par1 first possible choice
     * @param par2 second possible choice
     * @return a parameter based on the user's choice
     */

    public static String decision(String par1, String par2) {
        String decision = "";
        Scanner scanner = new Scanner(System.in);
        while (!decision.equalsIgnoreCase(par1) && !decision.equalsIgnoreCase(par2)) {
            System.out.print("$ ");
            decision = scanner.nextLine();
        }
        return decision;
    }

    /**
     * @param par1 first possible choice
     * @param par2 second possible choice
     * @param par3 third possible choice
     * @return a parameter based on the user's choice
     */

    public static String decision(String par1, String par2, String par3) {
        String decision = "";
        Scanner scanner = new Scanner(System.in);
        while (!decision.equalsIgnoreCase(par1)
                && !decision.equalsIgnoreCase(par2)
                && !decision.equalsIgnoreCase(par3)) {
            System.out.print("$ ");
            decision = scanner.nextLine();
        }
        return decision;
    }
}