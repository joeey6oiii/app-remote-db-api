package commands;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Command processing class.
 *
 * @authors Dmitrii Chebanenko and Alexey
 */
public class CommandHandler {
    /**
     * Field to store all commands.
     */
    private static Map<String, BaseCommand> map;
    /**
     * Field for storing executed commands.
     */
    private static ArrayList<BaseCommand> history;

    static {
        map = new LinkedHashMap<>();
        history = new ArrayList<>();
        map.put("add", new AddToCollection());
        map.put("info", new Info());
        map.put("show", new Show());
        map.put("clear", new Clear());
        map.put("save", new Save());
        map.put("exit", new Exit());
        map.put("remove_by_id", new RemoveById());
        map.put("help", new Help());
        map.put("update", new UpdateById());
        map.put("history", new CommandHistory());
        map.put("sum_of_height", new SumOfHeight());
        map.put("average_of_height", new AverageOfHeight());
        map.put("print_field_descending_birthday", new PrintFieldDescendingBirthday());
        map.put("execute_script", new ExecuteScript());
        map.put("remove_greater", new RemoveGreater());
        map.put("remove_lower", new RemoveLower());
    }

    /**
     * Method for processing commands with CommandHandler or with ExecuteScript.
     *
     * @param str - command from CommandHandler or from ExecuteScript
     */

    public static void handleCommand(String str) {
        var a = str.split(" ");
        try {
            if (a.length > 1) {
                map.get(a[0]).setParameter(a[1]);
            }
            map.get(a[0]).execute();
            history.add(map.get(a[0]));
        } catch (Exception e) {
            System.out.println("Invalid command. Type \"help\" to see a list of available commands and their description");
        }
    }

    /**
     * Method for getting a list containing available commands.
     *
     * @return returns a list of commands
     */
    public static Map<String, BaseCommand> getMap() {
        return map;
    }

    /**
     * Method for getting command execution history.
     *
     * @return history of used commands
     */
    public static ArrayList<BaseCommand> getHistory() {
        return history;
    }
}