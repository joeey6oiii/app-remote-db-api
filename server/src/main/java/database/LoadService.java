package database;

import defaultClasses.Person;

import java.util.List;

public class LoadService {

    public void loadToDatabase(List<Person> list) {
        Database database = Database.getInstance();
        list.stream().forEach(database::add);
    }

}
