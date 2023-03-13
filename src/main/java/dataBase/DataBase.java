package dataBase;

import defaultClasses.Person;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * DataBase class - a database containing objects of the Person class
 *
 * @author Dmitrii Chebanenko
 */
public class DataBase {
    /**
     * Field dataBase {@link DataBase#dataBase} - contains objects of class Person
     */
    private HashSet<Person> dataBase = new HashSet<>();
    /**
     * Field initializationTime {@link DataBase#initializationTime} - stores the collection initialization time
     */
    private LocalDateTime initializationTime;

    /**
     * Base constructor - initializes the field {@link DataBase#initializationTime}
     */
    public DataBase() {
        initializationTime = LocalDateTime.now();
    }

    /**
     * Sorts the collection using the Person class comparator
     */
    public void SortCollection() {
        dataBase = dataBase.stream().sorted(Person::compareTo).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * return dataBase
     *
     * @return dataBase
     */
    public HashSet<Person> getCollection() {
        return dataBase;
    }

    /**
     * @return dataBase size
     */
    public long getSizeOfTheCollection() {
        return dataBase.size();
    }

    /**
     * @return InitializationTime
     */

    public LocalDateTime getInitializationTime() {
        return initializationTime;
    }

    /**
     * @return Type
     */
    public Class getTypeOfTheCollection() {
        return dataBase.getClass();
    }
}
