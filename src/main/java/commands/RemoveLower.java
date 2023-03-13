package commands;

import dataBase.DataBase;
import dataBase.HeightComparator;
import defaultClasses.Person;
import generators.PersonGenerator;

import java.io.IOException;
import java.util.Iterator;

/**
 * The class that implements the remove_lower command - removes Person elements from the DataBase whose height field is less than the height field of the created object
 *
 * @author Dmitrii Chebanenko
 */
public class RemoveLower extends BaseCommand {
    /**
     * Method that removes Person elements from the DataBase whose height field is less than the height field of the created object
     *
     * @param obj - link to the database containing the collection
     * @throws IOException
     */
    public void execute(DataBase obj) throws IOException {
        PersonGenerator personGenerator = new PersonGenerator();
        Person person = personGenerator.generate();
        Iterator<Person> it = obj.getCollection().iterator();
        HeightComparator heightComparator = new HeightComparator();
        while (it.hasNext()) {
            if (heightComparator.compare(it.next(), person) < 0) {
                System.out.println();
                it.remove();
            }
        }
    }

    /**
     * Method that displays the description of the command
     */
    public void describe() {
        System.out.println("Removes from the collection all elements lower than the specified");
    }
}
