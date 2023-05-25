package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.CommandExecutionRequestSender;
import clientModules.response.handlers.ExecutionResultHandler;
import commands.CommandDescription;
import commandsModule.handler.CommandHandler;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;
import requests.CommandExecutionRequest;
import response.responses.ExecutionResultResponse;

import java.io.IOException;
import java.io.StreamCorruptedException;

/**
 * A class that represents the command execution result receiver.
 */

public class ExecutionResultReceiver implements CommandReceiver {

    /**
     * A method that receives the simplified command, sends request to a server, gets response and
     * calls the {@link ExecutionResultHandler#handleResponse(ExecutionResultResponse)} method.
     *
     * @param cmd simplified command
     * @param args simplified command arguments
     * @param module client core
     */

    @Override
    public void receiveCommand(CommandDescription cmd, String[] args, DataTransferConnectionModule module) {
        CommandExecutionRequest request = new CommandExecutionRequest(cmd, args);
        ExecutionResultResponse resultResponses;
        try {
            resultResponses = new CommandExecutionRequestSender().sendRequest(module, request);

            new ExecutionResultHandler().handleResponse(resultResponses);

            CommandHandler.getMissedCommands().remove(cmd, args);
        } catch (StreamCorruptedException | ServerUnavailableException | ResponseTimeoutException e) {
            CommandHandler.getMissedCommands().put(cmd, args);
        } catch (IOException e) {
            System.out.println("Something went wrong during I/O operations");
        } catch (NullPointerException e) {
            System.out.println("Unexpected error: Empty response received");
        }
    }

}
