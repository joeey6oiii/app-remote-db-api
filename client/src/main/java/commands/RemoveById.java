package commands;

import dataBase.GlobalObj;
import defaultClasses.Person;

import java.io.IOException;
import java.util.Iterator;

/**
 *
 Class for implementing the remove_by_id command - removes an element from the DataBase by the id specified in the parameter field of the BaseCommand parent class
 *
 * @author Dmitrii Chebanenko
 */
public class RemoveById extends BaseCommand {
    /**
     * Method that removes an element from the DataBase by id
     *
     * @throws IOException
     */
    public void execute() throws IOException {
        try {
            Integer a = Integer.parseInt(super.getParameter());
            var array = GlobalObj.dataBase.getCollection();
            Iterator<Person> it = array.iterator();
            while (it.hasNext()) {
                if (it.next().getId().equals(a)) {
                    it.remove();
                    System.out.println("Element with id " + a + " has been successfully removed");
                    return;
                }
            }
            System.out.println("Element with id " + a + " not found");
        } catch (Exception e) {
            System.out.println("Incorrect argument, command cannot be executed");
        }

    }

    /**
     * Method that returns the description of the command.
     */
    @Override
    public String describe() {
        return "Removes an element from the database by id";
    }
}
