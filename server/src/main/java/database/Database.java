package database;

import defaultClasses.Person;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Database {

    private static Database singleInstance;

    private HashSet<Person> database;

    private final LocalDateTime initializationTime;

    private Database() {
        database = new HashSet<>();
        initializationTime = LocalDateTime.now();
    }

    public static Database getInstance() {
        if (singleInstance == null) {
            singleInstance = new Database();
        }
        return singleInstance;
    }

    public HashSet<Person> getCollection() {
        return this.database;
    }

    public LocalDateTime getInitializationTime() {
        return this.initializationTime;
    }

    public void sort() {
        database = database.stream().sorted(Person::compareTo).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void clear() {
        this.database.clear();
    }

    public void add(Person person) {
        this.database.add(person);
        this.sort();
    }

    public boolean remove(int id) {
        return database.removeIf(person -> person.getId().equals(id));
    }
}