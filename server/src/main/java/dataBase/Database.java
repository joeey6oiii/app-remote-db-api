package dataBase;

import commandsModule.commands.BaseCommand;
import comparators.HeightComparator;
import defaultClasses.Person;
import yamlsTools.GlobalPath;
import yamlsTools.YAMLWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Database {

    private HashSet<Person> dataBase = new HashSet<>();

    private final LocalDateTime initializationTime;

    public Database() {
        initializationTime = LocalDateTime.now();
    }

    public void sortCollection() {
        dataBase = dataBase.stream().sorted(Person::compareTo).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public long getSizeOfTheCollection() {
        return dataBase.size();
    }

    public LocalDateTime getInitializationTime() {
        return initializationTime;
    }

    public Class getTypeOfTheCollection() {
        return dataBase.getClass();
    }

    public void add(Person person) {
        this.dataBase.add(person);
        this.sortCollection();
    }

    public void averageOfHeight() {
        Iterator<Person> it = this.dataBase.iterator();
        int sum = 0;
        int count = 0;
        while (it.hasNext()){
            Person person = it.next();
            sum += person.getHeight();
            count += 1;
        }
        System.out.println("The average \"height\" value is: " + sum/count);
    }

    public void clear() {
        Iterator<Person> i = this.dataBase.iterator();
        while (i.hasNext()) {
            i.next();
            i.remove();
        }
        System.out.println("Collection has been cleared");
    }

    public void history(Map<String, BaseCommand> map, ArrayList<BaseCommand> list) {
        if (list.isEmpty()) {
            System.out.println("No command history yet");
            return;
        }
        List<String> history = list.stream()
                .map(command -> map.entrySet().stream()
                        .filter(entry -> command.equals(entry.getValue()))
                        .map(Map.Entry::getKey)
                        .findFirst().orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        int i = 9;
        if (history.size() > i) {
            history = history.subList(history.size() - i, history.size());
        }
        System.out.println(history);
    }

    public void update(Integer id, Person person) {
        boolean found = false;
        for (Person p : this.dataBase) {
            if (Objects.equals(p.getId(), id)) {
                found = true;
                this.dataBase.remove(p);
                person.setId(id);
                this.add(person);
            }
        }
        if (!found) {
            System.out.println("No person matches id=" + id);
        }
    }

    public void sumOfHeight() {
        Iterator<Person> it = this.dataBase.iterator();
        int sum = 0;
        while (it.hasNext()){
            Person person = it.next();
            sum += person.getHeight();
        }
        System.out.println("Sum of \"height\" values is: " + sum);
    }

    public void show() {
        if (this.dataBase.size() == 0)
            System.out.println("Collection is empty");
        for (var a : this.dataBase) {
            System.out.println(a.toString());
        }
    }

    public void removeLower(Person person) {
        Iterator<Person> it = this.dataBase.iterator();
        HeightComparator heightComparator = new HeightComparator();
        while (it.hasNext()) {
            if (heightComparator.compare(it.next(), person) < 0) {
                System.out.println();
                it.remove();
            }
        }
    }

    public void removeGreater(Person person) {
        Iterator<Person> it = this.dataBase.iterator();
        HeightComparator heightComparator = new HeightComparator();
        while (it.hasNext()) {
            if (heightComparator.compare(it.next(), person) > 0) {
                it.remove();
            }
        }
    }

    public void remove(Integer id) {
        try {
            var array = this.dataBase;
            Iterator<Person> it = array.iterator();
            while (it.hasNext()) {
                if (it.next().getId().equals(id)) {
                    it.remove();
                    System.out.println("Element with id " + id + " has been successfully removed");
                    return;
                }
            }
            System.out.println("Element with id " + id + " not found");
        } catch (Exception e) {
            System.out.println("Incorrect argument, command cannot be executed");
        }
    }

    public void printFieldDescendingBirthday() {
        ArrayList<Date> list = new ArrayList<>(this.dataBase.size());
        for (Person person : this.dataBase) {
            list.add(person.getBirthday());
        }
        Collections.sort(list);
        Collections.reverse(list);
        for (Date date : list) {
            System.out.println(date);
        }
    }

    public void info() {
        System.out.println("Type: " + this.getTypeOfTheCollection());
        System.out.println("Length: " + this.getSizeOfTheCollection());
        System.out.println("Initialization Time: " + this.getInitializationTime());
    }

    public void help(Map<String, BaseCommand> map) {
        String command = "";
        for (Map.Entry<String, BaseCommand> entry : map.entrySet()) {
            if (entry.getKey().length() > command.length()) {
                command = entry.getKey();
            }
        }
        int commandLength = command.length();
        int emptyStringLength;
        for (Map.Entry<String, BaseCommand> entry : map.entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            emptyStringLength = commandLength + 4 - entry.getKey().length();
            while (emptyStringLength != 0) {
                stringBuilder.append(" ");
                emptyStringLength -= 1;
            }
            System.out.println(entry.getKey() + stringBuilder + entry.getValue().describe());
        }
    }

    public void save() {
        YAMLWriter yamlWriter = new YAMLWriter();
        try {
            yamlWriter.writeYAML(this.dataBase, "orderOutput.yaml");
        } catch (IOException e) {
            System.out.println("Something went wrong, the file was not created, please, try again");
        }
        System.out.println("The collection has been saved. The path to the file is: \"" + GlobalPath.getPath() + "orderOutput.yaml\"");
    }

}