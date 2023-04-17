package commands;

import dataBase.GlobalObj;
import dataBase.Loader;
import defaultClasses.Person;
import generators.PersonGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for implementing the add command (adds an element to the collection).
 *
 * @author Dmitrii Chebanenko
 */
public class AddToCollection extends BaseCommand {
    private final String name = "add";
    /**
     * The base method for the BaseCommand class {@see BaseCommand#execute()}, when called, allows
     *  the user to create an object of the Person class and add it to the collection via loader.
     */
    public void execute() {
        PersonGenerator personGenerator = new PersonGenerator();
        Person person = personGenerator.generate();
        List<Person> list = new ArrayList<>();
        list.add(person);
        new Loader().load(GlobalObj.dataBase, list);
    }

    /**
     * Method that returns the description of the command.
     */
    public String describe() {
        return "Creates and adds an element to the collection";
    }
}
