package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.CommandExecutionRequestSender;
import clientModules.response.handlers.ExecutionResultHandler;
import commands.CommandDescription;
import commandsModule.handler.CommandHandler;
import commandsModule.handler.CommandManager;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;
import org.apache.commons.io.IOUtils;
import requests.CommandExecutionRequest;
import response.responses.ExecutionResultResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

/**
 * A class that represents the script command receiver.
 */

public class ScriptCommandReceiver implements CommandReceiver {
    public static LinkedList<String> historyOfDangerScript = new LinkedList<>();

    /**
     * A method that receives the simplified "execute_script" command, parses file, sends request to a server,
     * gets response and calls the {@link ExecutionResultHandler#handleResponse(ExecutionResultResponse)} method.
     * After, iterates through <code>String[]</code> of commands from the parsed file
     * and calls the {@link CommandManager#manageCommand(CommandDescription, String[], DataTransferConnectionModule)} method.
     * Stores "history of danger scripts" collection to avoid looping
     *
     * @param cmd simplified command
     * @param args simplified command arguments
     * @param module client core
     */

    @Override
    public void receiveCommand(CommandDescription cmd, String[] args, DataTransferConnectionModule module) {
        if (historyOfDangerScript.contains(args[0])) {
            System.out.println("Detected dangerous command: the script will loop if the command is executed\n" +
                    "Continuing executing script from the next command...");
            historyOfDangerScript.clear();
            return;
        }
        File file = new File(args[1]);
        if (!file.exists()) {
            System.out.println("File not found. Returning to the console input");
            return;
        }
        CommandManager cm = new CommandManager();
        try (InputStream in = new FileInputStream(file)) {
            String contents = IOUtils.toString(in, StandardCharsets.UTF_8);
            String[] splitStr;
            var a = contents.split("\n");
            try {
                new ExecutionResultHandler().handleResponse(new CommandExecutionRequestSender()
                        .sendRequest(module, new CommandExecutionRequest(cmd, args)));
            } catch (StreamCorruptedException | ServerUnavailableException | ResponseTimeoutException e) {
                CommandHandler.getMissedCommands().put(cmd, args);
                return;
            } catch (NullPointerException e) {
                System.out.println("Unexpected error: Empty response received");
                return;
            }
            CommandHandler.getMissedCommands().remove(cmd, args);
            for (var t : a) {
                splitStr = t.split(" ");
                if (splitStr[0].equals("execute_script")) {
                    historyOfDangerScript.add(args[0]);
                }
                CommandDescription command = CommandHandler.getCommandByName(splitStr[0].toLowerCase());
                if (command != null) {
                    cm.manageCommand(command, splitStr, module);
                } else {
                    System.out.println("Command \"" + splitStr[0] + "\" Was Not Recognized as an" +
                            " Internal or External Command\nContinuing executing script...");
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to continue executing script. Returning to the console input");
        }
        historyOfDangerScript.clear();
    }

}
