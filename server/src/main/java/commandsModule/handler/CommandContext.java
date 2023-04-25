package commandsModule.handler;

import commandsModule.commands.BaseCommand;

import java.util.ArrayList;
import java.util.Map;

public interface CommandContext {

    Map<String, BaseCommand> getCommands();

    ArrayList<BaseCommand> getHistory();

}
