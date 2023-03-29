package commands;

import dataBase.DataBase;
import defaultClasses.Person;

import java.io.IOException;
import java.util.Iterator;

/**
 * A class that implements the average_of_height command.
 */

public class AverageOfHeight extends BaseCommand {

    /**
     * When called, sums the height field values of all {@link Person} objects and counts the amount of objects in the
     * collection, then outputs the average value by dividing sum by count.
     *
     * @param obj link to the database which contains the collection
     * @throws IOException
     */

    @Override
    public void execute(DataBase obj) throws IOException {
        Iterator<Person> it = obj.getCollection().iterator();
        int sum = 0;
        int count = 0;
        while (it.hasNext()){
            Person person = it.next();
            sum += person.getHeight();
            count += 1;
        }
        System.out.println("The average \"height\" value is: " + sum/count);
    }

    /**
     * A method that outputs the description of the command.
     */

    @Override
    public void describe() {
        System.out.println("Outputs the average value of the \"height\" field of all elements in the database");
    }
}
