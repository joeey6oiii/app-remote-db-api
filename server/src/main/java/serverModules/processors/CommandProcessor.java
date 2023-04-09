package serverModules.processors;

import commands.BaseCommand;

public class CommandProcessor implements ProcessAble<BaseCommand> {

    @Override
    public BaseCommand process(BaseCommand command) {
        // Perform some processing on the command
        return command;
    }
}