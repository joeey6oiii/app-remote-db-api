package commandsModule.handler;

import commands.CommandDescription;
import commandsModule.commands.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.CommandExecutionRequest;
import responses.ExecutionResultResponse;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;
import serverModules.response.sender.ExecutionResultResponseSender;

import java.io.IOException;
import java.util.*;

/**
 * A class that contains and executes commands.
 */

public class CommandHandler {
    private static final Logger logger = LogManager.getLogger("logger.CommandHandler");
    private final Map<String, BaseCommand> commands;
    private static List<BaseCommand> history;

    /**
     * A constructor for a CommandHandler.
     * Creates a command collection and fills it with the available commands.
     * Creates a history list where executed commands will be stored.
     */

    public CommandHandler() {
        commands = new LinkedHashMap<>();
        history = getHistory();

        commands.put("add", new AddCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("clear", new ClearCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("help", new HelpCommand(this.commands));
        commands.put("exit", new ExitCommand());
        commands.put("update_by_id", new UpdateByIdCommand());
        commands.put("history", new HistoryCommand(this.commands));
        commands.put("sum_of_height", new SumOfHeightCommand());
        commands.put("average_of_height", new AverageOfHeightCommand());
        commands.put("print_field_descending_birthday", new PrintFieldDescendingBirthdayCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("remove_lower", new RemoveLowerCommand());
    }

    /**
     * A method for getting a list containing available commands.
     *
     * @return returns a list of commands
     */

    public Map<String, BaseCommand> getCommands() {
        return commands;
    }

    /**
     * A method for getting command execution history.
     *
     * @return history of used commands
     */

    public static List<BaseCommand> getHistory() {
        if (history == null) {
            history = new ArrayList<>();
        }
        return history;
    }

    /**
     * A method that returns {@link BaseCommand} inheritor by the specified {@link CommandDescription}.
     *
     * @param description a command description, by which the relevant command will be selected
     */

    public BaseCommand getCommandByDescription(CommandDescription description)  {
        return commands.get(description.getCommandName().toLowerCase());
    }

    /**
     * When called, checks if the received command is parametrized or not, then, according to check result, executes the
     * command with or without arguments. After, gets response from the command and adds command to the command history
     * list, then sends the response to the client.
     *
     * @param module server core. Used to send data
     * @param callerBack the client to whom to send response
     * @param request the request, received from the client
     */

    public void execute(ConnectionModule module, CallerBack callerBack, CommandExecutionRequest request) {
        StringBuilder response = new StringBuilder();
        StringBuilder chunk = new StringBuilder();
        ExecutionResultResponseSender sender = new ExecutionResultResponseSender();
        try {
            BaseCommand command = this.getCommandByDescription(request.getDescriptionCommand());
            if (command instanceof ParameterizedCommand parameterizedCommand) {
                parameterizedCommand.setArguments(request.getArgs());
                parameterizedCommand.execute();
                response.append(parameterizedCommand.getResponse());
            } else {
                command.execute();
                response.append(command.getResponse());
            }
            history.add(command);
        } catch (IllegalArgumentException | NullPointerException e) {
            response.append("Command has invalid argument(s)");
            logger.fatal(response.toString(), e);
        } catch (IndexOutOfBoundsException e) {
            response.append("Command has invalid number of arguments");
            logger.fatal(response.toString(), e);
        } catch (IOException e) {
            response.append("Something went wrong during I/O operations");
            logger.fatal(response.toString(), e);
        } catch (Exception e) {
            response.append("Unexpected error happened during command execution");
            logger.fatal(response.toString(), e);
        }

        int maxPacketSize = 2048;
        int chunkNumber = 1;
        int totalChunks = (int) Math.ceil(response.length() / (double) maxPacketSize);
        int currentResponseNumber = 1;
        
        while (response.length() > 0) {
            if (response.length() <= maxPacketSize) {
                chunk.append(response);
                response.setLength(0);
            } else {
                chunk.append(response.substring(0, maxPacketSize));
                response.delete(0, maxPacketSize);
            }

            ExecutionResultResponse resultResponse;
            if (chunkNumber == 1 && totalChunks <= 1) {
                resultResponse = new ExecutionResultResponse(new String(chunk));
            } else if (chunkNumber == totalChunks) {
                resultResponse = new ExecutionResultResponse(new String(chunk), -1, totalChunks);
            } else {
                resultResponse = new ExecutionResultResponse(new String(chunk), currentResponseNumber, totalChunks);
            }
            chunk.delete(0, chunk.length());

            sender.sendResponse(module, callerBack, resultResponse);

            chunkNumber++;
            currentResponseNumber++;
        }

    }

}