package commands;

import dataBase.GlobalObj;
import dataBase.HeightComparator;
import defaultClasses.Person;
import generators.PersonGenerator;

import java.io.IOException;
import java.util.Iterator;

/**
 * The class that implements the remove_greater command - removes Person elements from the DataBase height field, which are greater than the height field of the created object
 *
 * @author Dmitrii Chebanenko
 */
public class RemoveGreater extends BaseCommand {
    /**
     * Method that removes Person elements from the DataBase whose height field is greater than the height field of the created object
     * @throws IOException
     */
    public void execute() throws IOException {
        PersonGenerator personGenerator = new PersonGenerator();
        Person person = personGenerator.generate();
        Iterator<Person> it = GlobalObj.dataBase.getCollection().iterator();
        HeightComparator heightComparator = new HeightComparator();
        while (it.hasNext()) {
            if (heightComparator.compare(it.next(), person) > 0) {
                it.remove();
            }
        }
    }

    /**
     * Method that displays the description of the command
     */
    public void describe() {
        System.out.println("Removes from the collection all elements greater than the specified");
    }
}
