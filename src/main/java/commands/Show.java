package commands;

import dataBase.DataBase;

/**
 * The class that implements the show command - displays all elements from the DataBase
 *
 * @author Dmitrii Chebanenko
 */
public class Show extends BaseCommand {
    /**
     * Method that allows the user to see the contents of the collection
     *
     * @param obj - link to the database containing the collection
     */
    public void execute(DataBase obj) {
        if (obj.getCollection().size() == 0)
            System.out.println("Collection is empty");
        for (var a : obj.getCollection()) {
            System.out.println(a.toString());
        }
    }

    /**
     * Method that displays the description of the command
     */
    public void describe() {
        System.out.println("Prints to standard output all elements of the collection in string representation");
    }
}
