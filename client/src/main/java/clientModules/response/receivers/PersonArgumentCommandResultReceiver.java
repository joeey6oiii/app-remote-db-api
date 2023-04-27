package clientModules.response.receivers;

import clientModules.connection.ConnectionModule;
import clientModules.request.sender.SingleArgumentCommandExecutionRequestSender;
import clientModules.response.contentHandlers.CommandExecutionResultRCH;
import commands.CommandDescription;
import defaultClasses.Person;
import generators.PersonGenerator;
import requests.SingleArgumentCommandExecutionRequest;
import responses.CommandExecutionResultResponse;

public class PersonArgumentCommandResultReceiver implements CommandReceiver {

    @Override
    public void receiveCommand(CommandDescription cmd, String[] arr, ConnectionModule module) {
        Person p = new PersonGenerator().generate();
        SingleArgumentCommandExecutionRequest<Person> request = new SingleArgumentCommandExecutionRequest<>(cmd, arr, p);
        CommandExecutionResultResponse resultResponse = new SingleArgumentCommandExecutionRequestSender().sendRequest(module, request);
        new CommandExecutionResultRCH().handleResponseContent(resultResponse);
    }
}
