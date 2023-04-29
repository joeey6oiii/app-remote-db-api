package database;

import defaultClasses.Person;

public class IDService {
    private static Integer maxId;

    public static Person recalculateId(Person person) {
        maxId += 1;
        person.setId(maxId);
        return person;
    }

    protected static void setMaxId(Integer id) {
        maxId = id;
    }

}
