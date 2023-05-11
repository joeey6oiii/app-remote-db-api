package generators;

import defaultClasses.Color;
import defaultClasses.Coordinates;
import defaultClasses.Location;
import defaultClasses.Person;
import helpFun.Decision;
import helpFun.StringToDateParser;
import validators.*;

import java.util.Date;
import java.util.Scanner;

/**
 * A class that allows the user to create an object of class Person.
 *
 * @author Dmitrii Chebanenko
 */

public class PersonGenerator implements Generate {

    /**
     * Method that creates an object of class Person.
     *
     * @return Person
     */

    public Person generate(){
        Scanner scanner = new Scanner(System.in);
        Person person = new Person();
        System.out.print("Enter name \n$ ");
        String name = scanner.nextLine();
        while (!new NameValidator().validate(name)) {
            System.out.print("Incorrect name. Enter name again \n$ ");
            name = scanner.nextLine();
        }
        person.setName(name);
        System.out.println("Creating coordinates:");
        CoordinatesGenerator coordinatesGenerator = new CoordinatesGenerator();
        Coordinates coordinates = coordinatesGenerator.generate();
        while (!new CoordinatesValidator().validate(coordinates)) {
            System.out.print("Incorrect coordinates. Enter coordinates again. \n");
            coordinates = coordinatesGenerator.generate();
        }
        person.setCoordinates(coordinates);
        System.out.print("Enter height (Long) \n$ ");
        int height = -10;
        while (!new HeightValidator().validate(height)) {
            try {
                height = Integer.parseInt(scanner.next());
                if (!new HeightValidator().validate(height))
                    System.out.print("Incorrect height. Enter height again \n$ ");
            } catch (Exception e) {
                System.out.print("Incorrect height. Enter height again \n$ ");
            }

        }
        person.setHeight(height);
        System.out.print("Enter birthday in format yyyy-MM-dd HH:mm:ss \n$ ");
        String test = scanner.nextLine(); // он кушает /n в буфере
        test = scanner.nextLine();
        boolean flag = true;
        Date date = null;
        while (flag) {
            try {
                    date = StringToDateParser.parse(test);
                    flag = false;
            } catch (Exception e){
                System.out.print("Incorrect. Enter the date in format yyyy-MM-dd HH:mm:ss\n$ ");
                test = scanner.nextLine();
            }
        }
        person.setBirthday(date);
        System.out.print("Enter passportID \n$ ");
        String passportId = scanner.next();
        while(!new PassportIDValidator().validate(passportId)) {

            System.out.print("Incorrect. passportId  must be equal to or greater than 5 \n$ ");

            passportId = scanner.next();
        }
        person.setPassportID(passportId);
        System.out.println("Choose one of the hair colors");
        System.out.println(Color.listValues());
        System.out.print("If you don't want to chose hair color press \"N\"\n$ ");
        String decision = "N";
        String str;
        str = scanner.next();
        if (!str.toUpperCase().equals("N")){
            while(!str.toUpperCase().equals("N")) {
                if (Color.getColorByName(str.toLowerCase()) != null) {
                    person.setHairColor(Color.getColorByName(str.toLowerCase()));
                    break;
                } else {
                    System.out.print("Your color is null. If you don't want to chose hair color press \"N\"\n$ ");
                    str = scanner.next();
                }
            }
        }
        System.out.println("Creating Location:");
        LocationGenerator locationGenerator = new LocationGenerator();
        Location location = locationGenerator.generate();
        decision = "N";
        while((!new LocationValidator().validate(location) && decision == "N") || location == null){
            if (location==null){
                System.out.println("Your location is null. Would you like to create null location? Type [Y/N]");
                decision = Decision.decision("Y", "N");
                if (decision.equalsIgnoreCase("Y"))
                    break;
            }
            location = locationGenerator.generate();
        }
        person.setLocation(location);
        return person;
    }
}