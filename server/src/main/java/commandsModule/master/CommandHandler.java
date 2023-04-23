package commandsModule.master;

import commands.CommandDescription;
import commandsModule.commands.*;
import dataBase.DataBase;
import exceptions.UnsupportedNumberOfArgumentsException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Command processing class.
 *
 * @authors Dmitrii Chebanenko and Alexey Kseikoff
 */

public class CommandHandler implements CommandContext {
    private final Map<String, BaseCommand> commands;
    private final ArrayList<BaseCommand> history;

    public CommandHandler(DataBase dataBase) {

        commands = new LinkedHashMap<>();
        history = new ArrayList<>();

        commands.put("add", new AddCommand(dataBase));
        commands.put("info", new InfoCommand(dataBase));
        commands.put("show", new ShowCommand(dataBase));
        commands.put("clear", new ClearCommand(dataBase));
        commands.put("save", new SaveCommand(dataBase));
        commands.put("remove_by_id", new RemoveByIdCommand(dataBase));
        commands.put("help", new HelpCommand(dataBase, this));
        commands.put("exit", new ExitCommand());
        commands.put("update", new UpdateByIdCommand(dataBase));
        commands.put("history", new HistoryCommand(dataBase, this));
        commands.put("sum_of_height", new SumOfHeightCommand(dataBase));
        commands.put("average_of_height", new AverageOfHeightCommand(dataBase));
        commands.put("print_field_descending_birthday", new PrintFieldDescendingBirthdayCommand(dataBase));
        commands.put("execute_script", new ExecuteScriptCommand(this));
        commands.put("remove_greater", new RemoveGreaterCommand(dataBase));
        commands.put("remove_lower", new RemoveLowerCommand(dataBase));
    }

    public Map<String, BaseCommand> getCommands() {
        return commands;
    }

    public ArrayList<BaseCommand> getHistory() {
        return history;
    }

    public BaseCommand getByName(CommandDescription description)  {
        return commands.get(description.getCommandName());
    }

    public void handleCommand(String str) {
        var a = str.split(" ");
        try {
            if (a.length > 1) {
                if (commands.get(a[0]) instanceof ParameterizedCommand command) {
                    command.setParameter(a[1]);
                    command.execute();
                } else {
                    throw new UnsupportedNumberOfArgumentsException(commands.get(a[0]) +
                            " does not support the entered number of arguments");
                }
            } else {
                commands.get(a[0]).execute();
            }
            history.add(commands.get(a[0]));
        } catch (Exception e) {
            System.out.println("Invalid command. Type \"help\" to see a list of available commands and their description");
        }
    }

}