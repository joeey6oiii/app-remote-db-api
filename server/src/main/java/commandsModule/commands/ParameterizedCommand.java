package commandsModule.commands;

public interface ParameterizedCommand extends BaseCommand {

    void setArguments(String[] args);

    String[] getArguments();

}
