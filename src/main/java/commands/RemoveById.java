package commands;

import dataBase.DataBase;
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
     * @param obj - link to the database containing the collection
     * @throws IOException
     */
    public void execute(DataBase obj) throws IOException {
        try {
            Integer a = Integer.parseInt(super.getParameter());
            var array = obj.getCollection();
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
     * Method that displays the description of the command
     */
    @Override
    public void describe() {
        System.out.println("Removes an element from the database by id");
    }
}
