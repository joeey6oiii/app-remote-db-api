package database;

import defaultClasses.Person;

import java.util.List;

public class LoadService {

    public void loadToDatabase(List<Person> list) {
        Database database = Database.getInstance();
        list.forEach(database::add);
        Integer maxId = list.stream()
                .map(Person::getId)
                .max(Integer::compareTo)
                .orElse(1);
        IDService.setMaxId(maxId);
    }

}
