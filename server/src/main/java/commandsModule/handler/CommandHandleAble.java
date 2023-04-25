package commandsModule.handler;

import commands.CommandDescription;
import commandsModule.commands.BaseCommand;
import requests.CommandExecutionRequest;

public interface CommandHandleAble {

    void execute(CommandExecutionRequest request);

    BaseCommand getCommandByDescription(CommandDescription description);

}
