package commandsModule.commands;

public interface SingleArgumentCommand<T> {

    T getSingleArgument();

    void setSingleArgument(T argument);

}
