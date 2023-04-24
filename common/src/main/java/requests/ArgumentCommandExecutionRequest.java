package requests;

import commands.CommandDescription;

public class ArgumentCommandExecutionRequest<T> extends CommandExecutionRequest {
    private final T arg;

    public ArgumentCommandExecutionRequest(CommandDescription command, String[] args, T arg) {
        super(command, args);
        this.arg = arg;
    }

    public T getArg() {
        return this.arg;
    }
}
