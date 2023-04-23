package commandsModule.master;

import commands.CommandDescription;
import commandsModule.commands.*;
import dataBase.Database;
import exceptions.UnsupportedNumberOfArgumentsException;

import java.util.ArrayList;
import java.util.Arrays;
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

    public CommandHandler(Database dataBase) {

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

    @Override
    public Map<String, BaseCommand> getCommands() {
        return commands;
    }

    @Override
    public ArrayList<BaseCommand> getHistory() {
        return history;
    }

    public BaseCommand getByName(CommandDescription description)  {
        return commands.get(description.getCommandName());
    }

    public void handleCommand(String str) {
        var inputArray = str.split(" ");
        try {
            if (inputArray.length > 1) {
                if (commands.get(inputArray[0]) instanceof ParameterizedCommand command) {
                    command.setArguments(Arrays.copyOfRange(inputArray, 1, inputArray.length));
                    command.execute();
                    command.clearArguments();
                } else {
                    throw new UnsupportedNumberOfArgumentsException(commands.get(inputArray[0]) +
                            " does not support the entered number of arguments");
                }
            } else {
                commands.get(inputArray[0]).execute();
            }
            history.add(commands.get(inputArray[0]));
        } catch (Exception e) {
            System.out.println("Invalid command. Type \"help\" to see a list of available commands and their description");
        }
    }

}