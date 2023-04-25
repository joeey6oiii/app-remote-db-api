package serverModules.request.manager;

import commandsModule.handler.CommandHandleAble;
import exceptions.IllegalManagerArgumentException;
import requests.*;
import serverModules.context.ServerContextContainAble;
import serverModules.request.contentHandlers.*;

import java.util.LinkedHashMap;
import java.util.Optional;

public class RequestHandlerManager {
    private final LinkedHashMap<Class<? extends Request>, RequestContentHandleAble> handlers;

    {
        handlers = new LinkedHashMap<>();

        handlers.put(CommandDescriptionsRequest.class, new CommandDescriptionsRCH());
        handlers.put(CommandExecutionRequest.class, new CommandExecutionRCH());
        handlers.put(SingleArgumentCommandExecutionRequest.class, new ArgumentCommandExecutionRCH<>());
    }

    public void manageRequest(ServerContextContainAble context, CommandHandleAble handler) {
        try {
            Optional.ofNullable(handlers.get(context.getRequest().getClass())).orElseThrow(() ->
                    new IllegalManagerArgumentException("RequestHandlerManager contains illegal argument")).handleRequestContent(context, handler);
        } catch (IllegalManagerArgumentException e) {
            e.printStackTrace();
        }
    }
}