package serverModules.request.handlers;

import exceptions.IllegalManagerArgumentException;
import requests.*;
import serverModules.context.ServerContext;

import java.util.LinkedHashMap;
import java.util.Optional;

public class RequestHandlerManager {
    private final LinkedHashMap<Class<? extends Request>, HandleRequestAble> handlers;

    {
        handlers = new LinkedHashMap<>();

        handlers.put(ClientCommandsRequest.class, new ClientCommandsHandler());
        handlers.put(CommandExecutionRequest.class, new CommandHandler());
        handlers.put(SingleArgumentCommandExecutionRequest.class, new ArgumentCommandHandler<>());
    }

    public void manageRequest(ServerContext context) {
        try {
            Optional.ofNullable(handlers.get(context.getRequest().getClass())).orElseThrow(() ->
                    new IllegalManagerArgumentException("RequestHandlerManager contains illegal argument")).handleRequest(context);
        } catch (IllegalManagerArgumentException e) {
            e.printStackTrace();
        }
    }
}