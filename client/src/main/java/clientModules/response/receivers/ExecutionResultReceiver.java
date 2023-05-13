package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.CommandExecutionRequestSender;
import clientModules.response.handlers.ExecutionResultHandler;
import commands.CommandDescription;
import commandsModule.handler.CommandHandler;
import exceptions.ServerUnavailableException;
import requests.CommandExecutionRequest;
import responses.ExecutionResultResponse;
import responses.Response;

import java.io.IOException;

/**
 * A class that represents the command execution result receiver.
 */

public class ExecutionResultReceiver implements CommandReceiver {

    /**
     * A method
     * that receives the simplified command, sends request to a server,
     * gets response and calls the {@link ExecutionResultHandler#handleResponse(Response)} method.
     *
     * @param cmd simplified command
     * @param args simplified command arguments
     * @param module client core
     */

    @Override
    public void receiveCommand(CommandDescription cmd, String[] args, DataTransferConnectionModule module) {
        CommandExecutionRequest request = new CommandExecutionRequest(cmd, args);
        ExecutionResultResponse resultResponse;
        try {
            resultResponse = new CommandExecutionRequestSender().sendRequest(module, request);

            new ExecutionResultHandler().handleResponse(resultResponse);

            CommandHandler.getMissedCommands().remove(cmd, args);
        } catch (IOException e) {
            System.out.println("Something went wrong during I/O operations");
        } catch (ServerUnavailableException e) {
            CommandHandler.getMissedCommands().put(cmd, args);
        } catch (NullPointerException e) {
            System.out.println("Unexpected error: Empty response received");
        }
    }
}
