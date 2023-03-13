package commands;

import dataBase.DataBase;
import defaultClasses.Person;

import java.io.IOException;
import java.util.Iterator;

/**
 * A class that implements the sum_of_height command.
 */

public class SumOfHeight extends BaseCommand {

    /**
     * When called, sums the height field values of all {@link Person} objects in the collection, then outputs the resulting sum.
     *
     * @param obj link to the database which contains the collection
     * @throws IOException
     */

    @Override
    public void execute(DataBase obj) throws IOException {
        Iterator<Person> it = obj.getCollection().iterator();
        int sum = 0;
        while (it.hasNext()){
            Person person = it.next();
            sum += person.getHeight();
        }
        System.out.println("Sum of \"height\" values is: " + sum);
    }

    /**
     * A method that outputs the description of the command.
     */

    @Override
    public void describe() {
        System.out.println("Outputs the sum of the \"height\" values of all elements in the database");
    }
}
