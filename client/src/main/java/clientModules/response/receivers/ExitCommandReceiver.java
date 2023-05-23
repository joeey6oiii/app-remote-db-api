package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.CommandExecutionRequestSender;
import clientModules.response.handlers.ExitCommandHandler;
import commands.CommandDescription;
import commandsModule.handler.CommandHandler;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;
import requests.CommandExecutionRequest;
import responses.ExecutionResultResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A class that represents the exit command receiver.
 */

public class ExitCommandReceiver implements CommandReceiver {

    /**
     * A method that receives the simplified "exit" command, sends request to a server,
     * gets responses and calls the {@link ExitCommandHandler#handleResponses(java.util.HashMap)} method.
     *
     * @param cmd simplified command
     * @param args simplified command arguments
     * @param module client core
     */

    @Override
    public void receiveCommand(CommandDescription cmd, String[] args, DataTransferConnectionModule module) {
        System.out.println("Are you sure you want to exit? [Y/N]");
        Scanner scanner = new Scanner(System.in);
        String str = "";
        while (!str.equalsIgnoreCase("Y")) {
            System.out.print("$ ");
            str = scanner.nextLine();
            if (str.equalsIgnoreCase("N")) {
                System.out.println("Returning to the console input");
                CommandHandler.getMissedCommands().remove(cmd, args);
                return;
            }
        }
        CommandExecutionRequest request = new CommandExecutionRequest(cmd, args);
        HashMap<Integer, ExecutionResultResponse> resultResponses;
        try {
            resultResponses = new CommandExecutionRequestSender().sendRequest(module, request);

            new ExitCommandHandler().handleResponses(resultResponses);

            CommandHandler.getMissedCommands().remove(cmd, args);
        } catch (IOException e) {
            System.out.println("Something went wrong during I/O operations");
        } catch (ServerUnavailableException | ResponseTimeoutException e) {
            CommandHandler.getMissedCommands().put(cmd, args);
        } catch (NullPointerException e) {
            System.out.println("Unexpected error: Empty response received");
        }
    }

}
