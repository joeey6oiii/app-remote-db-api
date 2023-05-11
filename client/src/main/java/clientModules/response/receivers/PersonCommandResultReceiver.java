package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.SingleArgumentCommandExecutionRequestSender;
import clientModules.response.handlers.ExecutionResultHandler;
import commands.CommandDescription;
import commandsModule.handler.CommandHandler;
import defaultClasses.Person;
import exceptions.ServerUnavailableException;
import generators.PersonGenerator;
import requests.SingleArgumentCommandExecutionRequest;
import responses.ExecutionResultResponse;

import java.io.IOException;

public class PersonCommandResultReceiver implements CommandReceiver {

    @Override
    public void receiveCommand(CommandDescription cmd, String[] args, DataTransferConnectionModule module) {
        Person p = new PersonGenerator().generate();
        SingleArgumentCommandExecutionRequest<Person> request = new SingleArgumentCommandExecutionRequest<>(cmd, args, p);
        ExecutionResultResponse resultResponse;
        try {
            resultResponse = new SingleArgumentCommandExecutionRequestSender().sendRequest(module, request);

            new ExecutionResultHandler().handleResponse(resultResponse);
        } catch (IOException e) {
            System.out.println("Something went wrong during I/O operations");
            return;
        } catch (ServerUnavailableException e) {
            CommandHandler.getMissedCommands().put(cmd, args);
            return;
        } catch (NullPointerException e) {
            System.out.println("Unexpected error: Empty response received");
        }
        CommandHandler.getMissedCommands().remove(cmd, args);
    }
}
