package generators;

import defaultClasses.Coordinates;

import java.util.Scanner;

/**
 * A class that allows the user to create an object of class Coordinates
 *
 * @author Dmitrii Chebanenko
 */
public class CoordinatesGenerator implements Generate {
    /**
     * Method that creates an object of class Coordinates
     *
     * @return coordinates
     */
    public Coordinates generate() {
        try {
            Coordinates coordinates = new Coordinates();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter x (long) \n$ ");
            long x = Long.parseLong(scanner.nextLine());
            coordinates.setX(x);
            System.out.print("Enter y (int) \n$ ");
            int y = Integer.parseInt(scanner.nextLine());
            coordinates.setY(y);
            return coordinates;
        } catch (Exception e) {
            return null;
        }
    }

}
