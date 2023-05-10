package clientModules.response.receivers;

import clientModules.connection.DatagramConnectionModule;
import clientModules.request.sender.SingleArgumentCommandExecutionRequestSender;
import clientModules.response.handlers.ExecutionResultHandler;
import commands.CommandDescription;
import defaultClasses.Person;
import generators.PersonGenerator;
import requests.SingleArgumentCommandExecutionRequest;
import responses.ExecutionResultResponse;

public class PersonCommandResultReceiver implements CommandReceiver {

    @Override
    public void receiveCommand(CommandDescription cmd, String[] args, DatagramConnectionModule module)  {
        Person p = new PersonGenerator().generate();
        SingleArgumentCommandExecutionRequest<Person> request = new SingleArgumentCommandExecutionRequest<>(cmd, args, p);
        ExecutionResultResponse resultResponse = new SingleArgumentCommandExecutionRequestSender().sendRequest(module, request);
        new ExecutionResultHandler().handleResponse(resultResponse);
    }
}
