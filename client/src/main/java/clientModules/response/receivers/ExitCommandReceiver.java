package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.CommandExecutionRequestSender;
import clientModules.response.handlers.ExitCommandHandler;
import commands.CommandDescription;
import commandsModule.handler.CommandHandler;
import exceptions.ServerUnavailableException;
import requests.CommandExecutionRequest;
import responses.ExecutionResultResponse;
import responses.Response;

import java.io.IOException;
import java.util.Scanner;

/**
 * A class that represents the exit command receiver.
 */

public class ExitCommandReceiver implements CommandReceiver {

    /**
     * A method that receives the simplified "exit" command, sends request to a server,
     * gets response and calls the {@link ExitCommandHandler#handleResponse(Response)} method.
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
                System.out.print("Returning to the console input\n");
                try {
                    CommandHandler.getMissedCommands().remove(cmd, args);
                } catch (Exception ignored) {}
                return;
            }
        }
        CommandExecutionRequest request = new CommandExecutionRequest(cmd, args);
        ExecutionResultResponse resultResponse;
        try {
            resultResponse = new CommandExecutionRequestSender().sendRequest(module, request);

            new ExitCommandHandler().handleResponse(resultResponse);
        } catch (IOException e) {
            System.out.println("Something went wrong during I/O operations");
            return;
        } catch (ServerUnavailableException e) {
            CommandHandler.getMissedCommands().put(cmd, args);
            return;
        } catch (NullPointerException e) {
            System.out.println("Unexpected error: Empty response received");
            return;
        }
        CommandHandler.getMissedCommands().remove(cmd, args);
    }
}
