package generators;

import defaultClasses.Color;
import defaultClasses.Coordinates;
import defaultClasses.Location;
import defaultClasses.Person;
import utils.StringToDateParser;
import validators.*;

import java.util.Date;
import java.util.Scanner;

/**
 * A class that implements a generating ability.
 */

public class PersonGenerator implements Generate {

    /**
     * Method that creates an object of class Person.
     *
     * @return Person object
     */

    public Person generate() {
        Scanner scanner = new Scanner(System.in);
        Person person = new Person();

        System.out.print("Enter name\n$ ");
        String name = scanner.nextLine();
        while (!new NameValidator().validate(name)) {
            System.out.print("Invalid name. Please enter a valid name\n$ ");
            name = scanner.nextLine();
        }
        person.setName(name);

        System.out.println("Creating coordinates:");
        CoordinatesGenerator coordinatesGenerator = new CoordinatesGenerator();
        Coordinates coordinates = coordinatesGenerator.generate();
        while (!new CoordinatesValidator().validate(coordinates)) {
            System.out.println("Invalid coordinates. Please enter valid coordinates:");
            coordinates = coordinatesGenerator.generate();
        }
        person.setCoordinates(coordinates);

        System.out.print("Enter height\n$ ");
        int height = Integer.parseInt(scanner.next());
        while (!new HeightValidator().validate(height)) {
            try {
                System.out.print("Invalid height. Please enter a valid height\n$ ");
                height = Integer.parseInt(scanner.next());
            } catch (Exception ignored) {
            }
        }
        person.setHeight(height);

        System.out.print("Enter birthday. Use <<yyyy-MM-dd HH:mm:ss>> pattern\n$ ");
        scanner.nextLine();
        Date birthday;
        String input;
        while (true) {
            input = scanner.nextLine().trim();
            try {
                if (input.isEmpty()) {
                    throw new IllegalArgumentException();
                }
                birthday = StringToDateParser.parse(input);
                break;
            } catch (Exception e) {
                System.out.print("Invalid date. Please enter a valid date. Use <<yyyy-MM-dd HH:mm:ss>> pattern\n$ ");
            }
        }
        person.setBirthday(birthday);

        System.out.print("Enter passportID of length 5 or more\n$ ");
        String passportId = scanner.nextLine();
        while (!new PassportIDValidator().validate(passportId)) {
            System.out.print("Invalid passportId. Please enter a valid passportId of length 5 or more\n$ ");
            passportId = scanner.nextLine();
        }
        person.setPassportID(passportId);

        System.out.print("Choose hair color:\n" + Color.listValues() + "\n" +
                "Enter anything except color name to skip this operation\n$ ");
        String str = scanner.nextLine().trim();
        Color hairColor = null;
        if (!str.isEmpty()) {
            hairColor = Color.getColorByName(str.toLowerCase());
        }
        person.setHairColor(hairColor);

        System.out.println("Creating Location. Press \"ENTER\" to skip this operation");
        LocationGenerator locationGenerator = new LocationGenerator();
        Location location = locationGenerator.generate();
        while (!new LocationValidator().validate(location)) {
            location = locationGenerator.generate();
        }
        person.setLocation(location);

        return person;
    }

}