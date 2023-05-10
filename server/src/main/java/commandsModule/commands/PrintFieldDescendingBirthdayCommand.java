package commandsModule.commands;

import database.Database;
import defaultClasses.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PrintFieldDescendingBirthdayCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.PrintFieldDescendingBirthdayCommand");
    private String response;

    @Override
    public String getName() {
        return "print_field_descending_birthday";
    }

    @Override
    public String getResponse() {
        return this.response;
    }

    @Override
    public String describe() {
        return "Outputs the \"birthday\" values of all elements in the database in descending order";
    }

    @Override
    public void execute() throws IOException {
        Database database = Database.getInstance();
        try {
            if (database.getCollection().isEmpty()) {
                this.response = "Collection is empty, can not execute print_field_descending_birthday";
            } else {
                List<Date> list = database.getCollection()
                        .stream()
                        .map(Person::getBirthday)
                        .sorted(Collections.reverseOrder())
                        .toList();

                StringBuilder builder;
                builder = new StringBuilder(list.stream()
                        .map(Date::toString)
                        .collect(Collectors.joining("\n")));
                this.response = new String(builder);
            }
            logger.info("Executed PrintFieldDescendingBirthdayCommand");
        } catch (Exception e) {
            this.response = "Something went wrong during print_field_descending_birthday command execution...";
            logger.warn("PrintFieldDescendingBirthdayCommand was not executed", e);
        }
    }

}
