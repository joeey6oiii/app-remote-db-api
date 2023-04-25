package requests;

import commands.CommandDescription;

public class SingleArgumentCommandExecutionRequest<T> extends CommandExecutionRequest {
    private final T arg;

    public SingleArgumentCommandExecutionRequest(CommandDescription command, String[] args, T arg) {
        super(command, args);
        this.arg = arg;
    }

    public T getArg() {
        return this.arg;
    }
}
