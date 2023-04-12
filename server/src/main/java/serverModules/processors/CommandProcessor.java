package serverModules.processors;

import commands.BaseCommand;

import java.io.IOException;

public class CommandProcessor implements ProcessAble<BaseCommand> {

    @Override
    public BaseCommand process(BaseCommand command) throws IOException {
        // Perform some processing on the command
        return command;
    }
}