package commands;

import dataBase.DataBase;
import defaultClasses.Person;
import updaters.PersonUpdater;

import java.io.IOException;

/**
 * A class that implements the update_by_id command.
 */

public class UpdateById extends BaseCommand {

    /**
     * When called, iterates through the collection to find the {@link Person} object with the specified id. If not found,
     * outputs <code>String</code>, otherwise calls the {@link PersonUpdater#update(Person)} method on the found person.
     * @see PersonUpdater
     *
     * @param obj link to the database which contains the collection
     * @throws IOException
     */

    @Override
    public void execute(DataBase obj) throws IOException {
        boolean found = false;
        for (Person person : obj.getCollection()) {
            if (person.getId() == Long.parseLong(super.getParameter())) {
                found = true;
                new PersonUpdater().update(person);
            }
        }
        if (!found) {
            System.out.println("No person matches id=" + super.getParameter());
        }
    }

    /**
     * A method that outputs the description of the command.
     */

    @Override
    public void describe() {
        System.out.println("Allows to reassign values to objects in the database");
    }
}