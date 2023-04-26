package database;

import commandsModule.commands.BaseCommand;
import commandsModule.commands.SaveCommand;
import comparators.HeightComparator;
import defaultClasses.Person;
import responses.CommandExecutionResultResponse;
import serverModules.context.ServerContextContainAble;
import serverModules.response.sender.CommandExecutionResultResponseSender;
import YAMLTools.YAMLWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Database {

    private HashSet<Person> dataBase;

    private final LocalDateTime initializationTime;

    private ServerContextContainAble context;

    private final CommandExecutionResultResponseSender responseSender;

    public Database() {
        dataBase = new HashSet<>();
        initializationTime = LocalDateTime.now();
        responseSender = new CommandExecutionResultResponseSender();
    }

    // надо придумать че делать если не присвоили =)) NullPtr не хочеца
    public void setServerContext(ServerContextContainAble context) {
        this.context = context;
    }

    public void sortCollection() {
        dataBase = dataBase.stream().sorted(Person::compareTo).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void add(Person person) {
        this.dataBase.add(person);
        this.sortCollection();
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse("Element was added"));
    }

    public void averageOfHeight() {
        if (dataBase.isEmpty()) {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Database is empty, can not execute average_of_height"));
            return;
        }
        Iterator<Person> it = this.dataBase.iterator();
        int sum = 0;
        int count = 0;
        while (it.hasNext()){
            Person person = it.next();
            sum += person.getHeight();
            count += 1;
        }
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse("The average \"height\" value is " + sum/count));
    }

    public void clear() {
        Iterator<Person> i = this.dataBase.iterator();
        while (i.hasNext()) {
            i.next();
            i.remove();
        }
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse("Cleared the collection"));
    }

    public void history(Map<String, BaseCommand> map, ArrayList<BaseCommand> list) {
        if (list.isEmpty()) {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("No command history yet"));
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
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse(history.toString()));
    }

    public void update(Integer id, Person person) {
        boolean found = false;
        for (Person p : this.dataBase) {
            if (Objects.equals(p.getId(), id)) {
                found = true;
                this.dataBase.remove(p);
                person.setId(id);
                this.dataBase.add(person);
                responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                        new CommandExecutionResultResponse("Updated element with id " + id));
            }
        }
        if (!found) {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("No element matches id " + id));
        }
    }

    public void sumOfHeight() {
        Iterator<Person> it = this.dataBase.iterator();
        int sum = 0;
        while (it.hasNext()){
            Person person = it.next();
            sum += person.getHeight();
        }
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse("Sum of \"height\" values is " + sum));
    }

    public void show() {
        if (this.dataBase.size() == 0)
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Collection is empty"));
        StringBuilder builder = new StringBuilder();
        for (var a : this.dataBase) {
            builder.append(a).append("\n");
        }
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse(new String(builder)));
    }

    public void removeLower(Person person) {
        Iterator<Person> it = this.dataBase.iterator();
        HeightComparator heightComparator = new HeightComparator();
        while (it.hasNext()) {
            if (heightComparator.compare(it.next(), person) < 0) {
                it.remove();
            }
        }
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse("Removed elements whose height parameter is lower than " + person.getHeight()));
    }

    public void removeGreater(Person person) {
        Iterator<Person> it = this.dataBase.iterator();
        HeightComparator heightComparator = new HeightComparator();
        while (it.hasNext()) {
            if (heightComparator.compare(it.next(), person) > 0) {
                it.remove();
            }
        }
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse("Removed elements whose height parameter is greater than " + person.getHeight()));
    }

    public void remove(Integer id) {
        try {
            var array = this.dataBase;
            Iterator<Person> it = array.iterator();
            while (it.hasNext()) {
                if (it.next().getId().equals(id)) {
                    it.remove();
                    responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                            new CommandExecutionResultResponse("Removed element with id " + id));
                    return;
                }
            }
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("No element matches id " + id));
        } catch (Exception e) {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Incorrect argument, command cannot be executed"));
        }
    }

    public void printFieldDescendingBirthday() {
        List<Date> list = this.dataBase.stream()
                .map(Person::getBirthday)
                .sorted(Collections.reverseOrder())
                .toList();

        String result = list.stream()
                .map(Date::toString)
                .collect(Collectors.joining("\n"));

        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse(result));
    }

    public void info() {
        StringBuilder builder = new StringBuilder();
        builder.append("Type: ").append(this.dataBase.getClass()).append("\nLength: ")
                .append(this.dataBase.size()).append("\nInitialization Time: ").append(this.initializationTime);
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse(new String(builder)));
    }

    public void help(Map<String, BaseCommand> map) {
        String command = "";
        for (Map.Entry<String, BaseCommand> entry : map.entrySet()) {
            if (entry.getKey().length() > command.length()) {
                command = entry.getKey();
            }
        }
        int commandLength = command.length();

        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, BaseCommand> entry : map.entrySet()) {
            String formatString = "%-" + (commandLength + 4) + "s%s";
            builder.append((String.format(formatString, entry.getKey() + " ", entry.getValue().describe()))).append("\n");
        }
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse(new String(builder)));
    }

    public void save() {
        YAMLWriter yamlWriter = new YAMLWriter();
        try {
            yamlWriter.writeYAML(this.dataBase, "orderOutput.yaml"); // сохр в resources на сервере
        } catch (IOException e) {
            // logger
        }
    }

    public void exit() {
        new SaveCommand(this).execute(); // чота типа такого
    }

}