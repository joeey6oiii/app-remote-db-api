package commands;

import dataBase.GlobalObj;
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
     * @throws IOException
     */

    @Override
    public void execute() throws IOException {
        Iterator<Person> it = GlobalObj.dataBase.getCollection().iterator();
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
     * A method that returns the description of the command.
     */

    @Override
    public String describe() {
        return "Outputs the average value of the \"height\" field of all elements in the database";
    }
}
