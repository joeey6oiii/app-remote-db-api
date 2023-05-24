package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.SingleArgumentCommandExecutionRequestSender;
import clientModules.response.handlers.ExecutionResultHandler;
import commands.CommandDescription;
import commandsModule.handler.CommandHandler;
import defaultClasses.Person;
import exceptions.ResponseTimeoutException;
import exceptions.ServerUnavailableException;
import generators.PersonGenerator;
import requests.SingleArgumentCommandExecutionRequest;
import responses.ExecutionResultResponse;

import java.io.IOException;
import java.util.HashMap;

/**
 * A class that represents the person single argument command execution result receiver.
 */

public class PersonCommandResultReceiver implements CommandReceiver {

    /**
     * A method that receives the simplified uncommon argument command, sends request to a server, gets response
     * and calls the {@link ExecutionResultHandler} method.
     *
     * @param cmd simplified command
     * @param args simplified command arguments
     * @param module client core
     */

    @Override
    public void receiveCommand(CommandDescription cmd, String[] args, DataTransferConnectionModule module) {
        Person p = new PersonGenerator().generate();
        SingleArgumentCommandExecutionRequest<Person> request = new SingleArgumentCommandExecutionRequest<>(cmd, args, p);
        HashMap<Integer, ExecutionResultResponse> resultResponses;
        try {
            resultResponses = new SingleArgumentCommandExecutionRequestSender().sendRequest(module, request);

            new ExecutionResultHandler().handleResponses(resultResponses);

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
