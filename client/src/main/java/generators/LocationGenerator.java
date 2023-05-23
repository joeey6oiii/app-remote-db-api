package generators;

import defaultClasses.Location;

import java.util.Scanner;

/**
 * A class that allows the user to create an object of class Location.
 *
 * @author Dmitrii Chebanenko
 */

public class LocationGenerator implements Generate {

    /**
     * Method that creates an object of class Location.
     *
     * @return Location
     */

    public Location generate() {
        Scanner scanner = new Scanner(System.in);
        Location location = new Location();
        String input;

        System.out.print("Enter X coordinate\n$ ");
        while (true) {
            try {
                input = scanner.nextLine();
                if (input.isEmpty()) {
                    return null;
                } else {
                    Float x = Float.parseFloat(input);
                    location.setX(x);
                    break;
                }
            } catch (Exception e){
                System.out.print("Invalid coordinate X. Please enter a valid X coordinate\n$ ");
            }
        }

        System.out.print("Enter Y coordinate\n$ ");
        while (true) {
            try {
                input = scanner.nextLine();
                if (input.isEmpty()) {
                    return null;
                } else {
                    Integer y = Integer.parseInt(input);
                    location.setY(y);
                    break;
                }
            } catch (Exception e){
                System.out.print("Invalid coordinate Y. Please enter a valid Y coordinate\n$ ");
            }
        }

        System.out.print("Enter name. Press \"ENTER\" to skip this operation\n$ ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            location.setName(null);
        } else {
            location.setName(name);
        }

        return location;
     }

}
