package commands;

import dataBase.DataBase;

/**
 * Class for implementing the Info command - displays information about the database
 * @author Dmitrii Chebanenko
 */
public class Info extends BaseCommand {
    /**
     * Method displays information about the database (Type, Length, Initialization Time)
     * @param obj - link to the database containing the collection
     */
    public void execute(DataBase obj) {
        System.out.println("Type: " + obj.getTypeOfTheCollection());
        System.out.println("Length: " + obj.getSizeOfTheCollection());
        System.out.println("Initialization Time: " + obj.getInitializationTime());
    }
    /**
     * Method that displays the description of the command
     */
    public void describe() {
        System.out.println("Prints information about the collection to the standard" +
                " output stream (type, initialization date, number of elements, etc.)");
    }
}
