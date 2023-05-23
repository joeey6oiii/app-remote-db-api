package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.CommandExecutionRequestSender;
import clientModules.response.handlers.ExecutionResultHandler;
import commands.CommandDescription;
import commandsModule.handler.CommandHandler;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;
import requests.CommandExecutionRequest;
import responses.ExecutionResultResponse;

import java.io.IOException;
import java.util.HashMap;

/**
 * A class that represents the command execution result receiver.
 */

public class ExecutionResultReceiver implements CommandReceiver {

    /**
     * A method
     * that receives the simplified command, sends request to a server,
     * gets response and calls the {@link ExecutionResultHandler#handleResponses(HashMap)} method.
     *
     * @param cmd simplified command
     * @param args simplified command arguments
     * @param module client core
     */

    @Override
    public void receiveCommand(CommandDescription cmd, String[] args, DataTransferConnectionModule module) {
        CommandExecutionRequest request = new CommandExecutionRequest(cmd, args);
        ExecutionResultHandler handler = new ExecutionResultHandler();
        HashMap<Integer, ExecutionResultResponse> resultResponses = new HashMap<>();
        try {
            resultResponses = new CommandExecutionRequestSender().sendRequest(module, request);

            handler.handleResponses(resultResponses);

            CommandHandler.getMissedCommands().remove(cmd, args);
        } catch (IOException e) {
            System.out.println("Something went wrong during I/O operations");
        } catch (ServerUnavailableException e) {
            CommandHandler.getMissedCommands().put(cmd, args);
        } catch (ResponseTimeoutException e) {
            if (resultResponses.size() > 0) {
                System.out.println("Some of the data is missing due to an expired timeout for getting response from the server");
                handler.handleResponses(resultResponses);
            } else {
                CommandHandler.getMissedCommands().put(cmd, args);
            }
        } catch (NullPointerException e) {
            System.out.println("Unexpected error: Empty response received");
        }
    }

}
