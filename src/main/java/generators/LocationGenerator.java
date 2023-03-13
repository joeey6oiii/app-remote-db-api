package generators;

import defaultClasses.Location;

import java.util.Objects;
import java.util.Scanner;

/**
 * A class that allows the user to create an object of class Location
 *
 * @author Dmitrii Chebanenko
 */
public class LocationGenerator implements Generate {
    /**
     * Method that creates an object of class Location
     *
     * @return Location
     */
    public Location generate() {
        Scanner scanner = new Scanner(System.in);
        Location location = new Location();
        try {
            System.out.print("Enter x (Float) \n$ ");
            Float x = Float.parseFloat(scanner.nextLine());
            location.setX(x);
            System.out.print("Enter y (Integer) \n$ ");
            Integer y = Integer.parseInt(scanner.nextLine());
            location.setY(y);
            System.out.print("Enter name \n$ ");
            String name = scanner.nextLine();
            if (name.equals("")) {
                location.setName(null);
            } else {
                location.setName(name);
            }
            return location;
        } catch (Exception e) {
            return null;
        }
     }
}
