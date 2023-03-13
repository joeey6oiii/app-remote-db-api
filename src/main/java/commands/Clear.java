package commands;

import dataBase.DataBase;

import java.util.Iterator;

/**
 * Class for implementing the Clear command - removes all objects from the database
 *
 * @author Dmitrii Chebanenko
 */
public class Clear extends BaseCommand {
    /**
     * Executes the Clear command, which clears the entire database
     *
     * @param obj - link to the database containing the collection
     */
    public void execute(DataBase obj) {
        Iterator i = obj.getCollection().iterator();
        while (i.hasNext()) {
            i.next();
            i.remove();
        }
        System.out.println("Collection has been cleared");
    }

    /**
     * Method that displays the description of the command
     */
    public void describe() {
        System.out.println("Clears the collection");
    }
}
