package commands;

import dataBase.GlobalObj;
import defaultClasses.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * A class that implements the print_field_descending_birthday command.
 */

public class PrintFieldDescendingBirthday extends BaseCommand {

    /**
     * When called, creates an <code>ArrayList</code> and adds the values of the birthday field of all the {@link Person}
     * objects in the collection to it. Then, sorts, reverses and outputs the resulting list.
     *
     * @throws IOException
     */

    @Override
    public void execute() throws IOException {
        ArrayList<Date> list = new ArrayList<>(GlobalObj.dataBase.getCollection().size());
        for (Person person : GlobalObj.dataBase.getCollection()) {
            list.add(person.getBirthday());
        }
        Collections.sort(list);
        Collections.reverse(list);
        for (Date date : list) {
            System.out.println(date);
        }
    }

    /**
     * A method that returns the description of the command.
     */

    @Override
    public String describe() {
        return "Outputs the \"birthday\" values of all elements in the database in descending order";
    }
}
