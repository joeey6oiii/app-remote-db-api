package commandsModule.commands;

import java.io.IOException;

// currently out of order
public class ExitCommand implements BaseCommand {
    private String response;

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getResponse() {
        return this.response;
    }

    @Override
    public String describe() {
        return "Closes the program without saving";
    }

    @Override
    public void execute() throws IOException {
        new SaveCommand().execute();
    }

}
