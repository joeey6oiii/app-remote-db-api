package commands;

import dataBase.GlobalObj;

/**
 * Class for implementing the Info command - displays information about the database
 * @author Dmitrii Chebanenko
 */
public class Info extends BaseCommand {
    /**
     * Method displays information about the database (Type, Length, Initialization Time)
     */
    public void execute() {
        System.out.println("Type: " + GlobalObj.dataBase.getTypeOfTheCollection());
        System.out.println("Length: " + GlobalObj.dataBase.getSizeOfTheCollection());
        System.out.println("Initialization Time: " + GlobalObj.dataBase.getInitializationTime());
    }
    /**
     * Method that returns the description of the command
     */
    public String describe() {
        return "Prints information about the collection to the standard" +
                " output stream (type, initialization date, number of elements, etc.)";
    }
}
