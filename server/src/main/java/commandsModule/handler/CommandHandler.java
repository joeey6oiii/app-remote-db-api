package commandsModule.handler;

import commands.CommandDescription;
import commandsModule.commands.*;
import database.Database;
import requests.CommandExecutionRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Command processing class.
 *
 * @authors Dmitrii Chebanenko and Alexey Kseikoff
 */

public class CommandHandler implements CommandHandleAble, CommandContext {
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
        commands.put("exit", new ExitCommand(dataBase));
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

    @Override
    public BaseCommand getCommandByDescription(CommandDescription description)  {
        return commands.get(description.getCommandName());
    }

    @Override
    public void execute(CommandExecutionRequest request) {
        try {
            BaseCommand command = this.getCommandByDescription(request.getDescriptionCommand());
            if (command instanceof ParameterizedCommand parameterizedCommand) {
                parameterizedCommand.setArguments(request.getArgs());
                parameterizedCommand.execute();
                parameterizedCommand.clearArguments();
                history.add(parameterizedCommand);
                return;
            }
            command.execute();
            history.add(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}