package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.CommandExecutionRequestSender;
import clientModules.response.handlers.ExecutionResultHandler;
import commands.CommandDescription;
import commandsModule.handler.CommandHandler;
import exceptions.ServerUnavailableException;
import requests.CommandExecutionRequest;
import responses.ExecutionResultResponse;

import java.io.IOException;

public class ExecutionResultReceiver implements CommandReceiver {

    @Override
    public void receiveCommand(CommandDescription cmd, String[] args, DataTransferConnectionModule module) {
        CommandExecutionRequest request = new CommandExecutionRequest(cmd, args);
        ExecutionResultResponse resultResponse;
        try {
            resultResponse = new CommandExecutionRequestSender().sendRequest(module, request);

            new ExecutionResultHandler().handleResponse(resultResponse);
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
