package commands;

import dataBase.GlobalObj;
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
     * @throws IOException
     */

    @Override
    public void execute() throws IOException {
        Iterator<Person> it = GlobalObj.dataBase.getCollection().iterator();
        int sum = 0;
        while (it.hasNext()){
            Person person = it.next();
            sum += person.getHeight();
        }
        System.out.println("Sum of \"height\" values is: " + sum);
    }

    /**
     * A method that returns the description of the command.
     */

    @Override
    public String describe() {
        return "Outputs the sum of the \"height\" values of all elements in the database";
    }
}
