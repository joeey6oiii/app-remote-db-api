package commands;

import dataBase.GlobalObj;

import java.util.Iterator;

/**
 * Class for implementing the Clear command - removes all objects from the database.
 *
 * @author Dmitrii Chebanenko
 */
public class Clear extends BaseCommand {
    /**
     * Executes the Clear command, which clears the entire database
     *
     */
    public void execute() {
        Iterator i = GlobalObj.dataBase.getCollection().iterator();
        while (i.hasNext()) {
            i.next();
            i.remove();
        }
        System.out.println("Collection has been cleared");
    }

    /**
     * Method that returns the description of the command.
     */
    public String describe() {
        return "Clears the collection";
    }
}
