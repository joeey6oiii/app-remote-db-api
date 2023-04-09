package commands;

import dataBase.GlobalObj;

/**
 * The class that implements the show command - displays all elements from the DataBase
 *
 * @author Dmitrii Chebanenko
 */
public class Show extends BaseCommand {
    /**
     * Method that allows the user to see the contents of the collection
     *
     */
    public void execute() {
        if (GlobalObj.dataBase.getCollection().size() == 0)
            System.out.println("Collection is empty");
        for (var a : GlobalObj.dataBase.getCollection()) {
            System.out.println(a.toString());
        }
    }

    /**
     * Method that returns the description of the command.
     */
    public String describe() {
        return "Prints to standard output all elements of the collection in string representation";
    }
}
