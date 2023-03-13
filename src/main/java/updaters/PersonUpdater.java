package updaters;

import defaultClasses.*;
import generators.*;
import helpFun.*;
import validators.*;

import java.util.*;

/**
 * A class that implements the {@link UpdateAble} interface. Used to update fields of the {@link Person} object.
 * <p>
 * Uses <code>TreeMap</code> and <code>ArrayList</code> to store field names.
 */

public class PersonUpdater implements UpdateAble<Person> {
    private static final Map<String, Generate> treeMap;
    private static final List<String> list;

    static {
        treeMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        treeMap.put("hairColor", new ColorGenerator());
        treeMap.put("location", new LocationGenerator());
        treeMap.put("coordinates", new CoordinatesGenerator());

        list = new ArrayList<>(4);
        list.add("name");
        list.add("passportID");
        list.add("birthday");
        list.add("height");
    }

    /**
     * Used to update the field values of the {@link Person} object in the collection.
     *
     * @param person person whose fields to update
     */

    @Override
    public void update(Person person) {
        System.out.print("\u001B[33mPersonUpdater started\u001B[0m\n" +
                "Currently updating " + person + "\nEnter [Field name]" +
                " or \"exit\" to leave the PersonUpdater\n$ ");
        String field = "";
        Scanner scanner = new Scanner(System.in);
        while (!field.equalsIgnoreCase("EXIT")) {
            field = scanner.nextLine();
            if (field.equalsIgnoreCase("EXIT")) {
                break;
            } else if (treeMap.containsKey(field)) {
                Set<Map.Entry<String, Generate> > entrySet = treeMap.entrySet();
                Map.Entry<String, Generate>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);
                for (Map.Entry<String, Generate> stringGenerateEntry : entryArray) {
                    if (stringGenerateEntry.getKey().equalsIgnoreCase(field)) {
                        field = stringGenerateEntry.getKey();
                        break;
                    }
                }
                Class<?> fieldClass = null;
                var parameter = treeMap.get(field).generate();
                try {
                    fieldClass = person.getClass().getDeclaredField(field).getType();
                } catch (NoSuchFieldException ignored) {}
                var previous_value = Reflection.getValue(person, field);
                Reflection.setValue(person, field, fieldClass, parameter);
                if (new PersonValidator().validate(person)) {
                    System.out.print("Data updated: " + person + "\nEnter [Field name]" +
                            " to update anything else or \"exit\" to exit the PersonUpdater\n$ ");
                } else {
                    Reflection.setValue(person, field, fieldClass, previous_value);
                    System.out.print("\u001B[31mValidation failed\u001B[0m" + ", please, try again\n$ ");
                }
            } else if (list.stream().anyMatch(field::equalsIgnoreCase)) {
                for (String str : list) {
                    if (str.equalsIgnoreCase(field)) {
                        field = str;
                        break;
                    }
                }
                var previous_value = Reflection.getValue(person, field);
                boolean boolBirthday = field.equalsIgnoreCase("BIRTHDAY");
                boolean boolHeight = field.equalsIgnoreCase("HEIGHT");
                if (boolBirthday) {
                    System.out.print("Enter " + field + ". Format: yyyy-MM-dd HH:mm:ss\n$ ");
                    boolean checked = false;
                    String strBirthday;
                    Date birthday = null;
                    do {
                        try {
                            strBirthday = scanner.nextLine();
                            birthday = StringToDateParser.parse(strBirthday);
                            checked = true;
                        } catch (Exception e) {
                            System.out.print("Enter " + field + ". Format: yyyy-MM-dd HH:mm:ss\n$ ");
                        }
                    } while (!checked);
                    Reflection.setValue(person, field, Date.class, birthday);
                } else if (boolHeight) {
                    System.out.print("Enter " + field + "\n$ ");
                    boolean checked = false;
                    int height = 0;
                    do {
                        try {
                            height = Integer.parseInt(scanner.nextLine());
                            checked = true;
                        } catch (Exception e) {
                            System.out.print("Enter " + field + "\n$ ");
                        }
                    } while (!checked);
                    Reflection.setValue(person, field, int.class, height);
                } else {
                    System.out.print("Enter " + field + "\n$ ");
                    Reflection.setValue(person, field, String.class, scanner.nextLine());
                }
                if (new PersonValidator().validate(person)) {
                    System.out.print("Data updated: " + person + "\nEnter [Field name]" +
                            " to update anything else or \"exit\" to exit the PersonUpdater\n$ ");
                } else {
                    if (boolBirthday) {
                        Reflection.setValue(person, field, Date.class, previous_value);
                    } else if (boolHeight) {
                        Reflection.setValue(person, field, int.class, previous_value);
                    } else {
                        Reflection.setValue(person, field, String.class, previous_value);
                    }
                    System.out.print("\u001B[31mValidation failed\u001B[0m, please, try again\n$ ");
                }
            } else {
                System.out.print("No such field or field is unavailable to update\n$ ");
            }
        }
        System.out.println("\u001B[33mExit PersonUpdater\u001B[0m");
    }
}