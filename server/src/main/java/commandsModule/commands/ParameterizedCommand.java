package commandsModule.commands;

public interface ParameterizedCommand extends BaseCommand {
    String getParameter();
    void setParameter(String parameter); // пока что не String[]
}
