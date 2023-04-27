package commandsModule.commands;

import java.io.IOException;

public interface BaseCommand {

    String getName();

    String getResponse();

    String describe();

    void execute() throws IOException;

}