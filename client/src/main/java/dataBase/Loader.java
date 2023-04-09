package dataBase;

import defaultClasses.Person;
import validators.PersonValidator;

import java.util.List;

/**
 * A class that allows to load objects into a database collection.
 */

public class Loader {

    /**
     * Creates a new Loader.
     */

    public Loader() {}

    /**
     * A method for loading {@link Person} objects into a collection in the specified database.
     * <p>
     * Uses {@link PersonValidator#validate(Person)} to check the data of each {@link Person} object. If the object's data is valid,
     * adds the object to the collection in the specified database
     *
     * @param dataBase link to the database which contains the collection
     * @param people a list of {@link Person} objects
     */

    public void load(DataBase dataBase, List<Person> people) {
//        ArrayList<Person> invalidObjList = new ArrayList<>();
        for (Person person : people) {
            if (new PersonValidator().validate(person)) {
                dataBase.getCollection().add(person);
                dataBase.SortCollection();
//            } else {
//                invalidObjList.add(person);
//            }
            }
//        if (!invalidObjList.isEmpty()) {
//            try {
//                new YAMLWriter().writeYAML(invalidObjList, "InvalidObjects.yaml");
//            } catch (IOException ignored) {}
//            System.out.println("\u001B[33mCompleted data upload\n\nMessage:" +
//                    "\u001B[0m Some data is invalid, check the \"InvalidObjects.yaml\"" +
//                    " file\nin the path \"" + GlobalPath.getPath() + "InvalidObjects.yaml\"" +
//                    " to see which\nobjects were not added to the collection in the database\n");
//        } else {
//            System.out.println("\n\u001B[33mCompleted data upload\u001B[0m\n");
//        }
        }
    }
}