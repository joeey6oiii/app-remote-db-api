package commandsModule.commands;

import commands.CommandDescription;

import java.io.IOException;

public interface BaseCommand {

    String getName();

    String describe();

    void execute() throws IOException;

}