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

    private HashSet<Person> database;

    private final LocalDateTime initializationTime;

    private ServerContextContainAble context;

    private final CommandExecutionResultResponseSender responseSender;

    public Database() {
        database = new HashSet<>();
        initializationTime = LocalDateTime.now();
        responseSender = new CommandExecutionResultResponseSender();
    }

    public void setServerContext(ServerContextContainAble context) {
        this.context = context;
    }

    public void sortCollection() {
        database = database.stream().sorted(Person::compareTo).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void add(Person person) {
        this.database.add(person);
        this.sortCollection();
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse("Element was added"));
    }

    public void averageOfHeight() {
        if (database.isEmpty()) {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Collection is empty, can not execute average_of_height"));
            return;
        }

        double averageHeight = database.stream()
                .mapToInt(Person::getHeight)
                .average()
                .orElse(0);

        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse("The average \"height\" value is " + averageHeight));
    }


    public void clear() {
        if (this.database.isEmpty()) {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Collection is empty, there is nothing to clear"));
            return;
        }
        this.database.clear();
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
        Optional<Person> optionalPerson = this.database.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();
        if (optionalPerson.isPresent()) {
            Person existingPerson = optionalPerson.get();
            this.database.remove(existingPerson);
            person.setId(id);
            this.database.add(person);
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Updated element with id " + id));
        } else {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("No element matches id " + id));
        }
    }

    public void sumOfHeight() {
        if (this.database.isEmpty()) {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Collection is empty, can not execute sum_of_height"));
            return;
        }
        int sum = this.database.stream().mapToInt(Person::getHeight).sum();
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse("Sum of \"height\" values is " + sum));
    }

    public void show() {
        if (this.database.isEmpty()) {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Collection is empty, there is nothing to show"));
            return;
        }
        StringBuilder builder;
        builder = new StringBuilder(this.database.stream().map(Object::toString).collect(Collectors.joining("\n")));
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse(new String(builder.substring(0, builder.length() - 1))));
    }

    public void removeLower(Person person) {
        if (this.database.isEmpty()) {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Collection is empty, there is nothing to remove"));
            return;
        }
        this.database.removeIf(p -> new HeightComparator().compare(p, person) < 0);
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse("Removed elements whose height parameter is lower than " + person.getHeight()));
    }


    public void removeGreater(Person person) {
        if (this.database.isEmpty()) {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Collection is empty, there is nothing to remove"));
            return;
        }
        database.removeIf(p -> new HeightComparator().compare(p, person) > 0);
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse("Removed elements whose height parameter is greater than " + person.getHeight()));
    }

    public void remove(Integer id) {
        if (this.database.isEmpty()) {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Collection is empty, there is nothing to remove"));
            return;
        }
        try {
            this.database.removeIf(person -> person.getId().equals(id));
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Removed element with id " + id));
        } catch (Exception e) {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Incorrect argument, command cannot be executed"));
        }
    }

    public void printFieldDescendingBirthday() {
        if (this.database.isEmpty()) {
            responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                    new CommandExecutionResultResponse("Collection is empty, can not execute print_field_descending_birthday"));
            return;
        }
        List<Date> list = this.database.stream()
                .map(Person::getBirthday)
                .sorted(Collections.reverseOrder())
                .toList();

        StringBuilder builder;
        builder = new StringBuilder(list.stream()
                .map(Date::toString)
                .collect(Collectors.joining("\n")));

        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse(new String(builder)));
    }

    public void info() {
        StringBuilder builder = new StringBuilder();
        builder.append("Type: ").append(this.database.getClass()).append("\nLength: ")
                .append(this.database.size()).append("\nInitialization Time: ").append(this.initializationTime);
        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse(new String(builder)));
    }

    public void help(Map<String, BaseCommand> map) {
        int commandLength = map.keySet().stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
        String formatString = "%-" + (commandLength + 4) + "s%s\n";
        StringBuilder builder;
        builder = new StringBuilder(map.entrySet().stream()
                .map(entry -> String.format(formatString, entry.getKey() + " ", entry.getValue().describe()))
                .collect(Collectors.joining()));

        responseSender.sendResponse(context.getConnectionModule(), context.getCallerBack(),
                new CommandExecutionResultResponse(builder.substring(0, builder.length() - 1)));
    }

    public void save() {
        YAMLWriter yamlWriter = new YAMLWriter();
        try {
            yamlWriter.writeYAML(this.database, "orderOutput.yaml"); // сохр в resources на сервере
        } catch (IOException e) {
            // logger
        }
    }

    public void exit() {
        new SaveCommand(this).execute();
    }

}