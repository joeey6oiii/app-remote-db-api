package serverModules.request.manager;

import exceptions.IllegalManagerArgumentException;
import requests.*;
import serverModules.context.ServerContext;
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
        handlers.put(ArgumentCommandExecutionRequest.class, new ArgumentCommandExecutionRCH());
        handlers.put(ExitCommandExecutionRequest.class, new ExitCommandExecutionRCH());
        // script execution command ??
    }

    public void manageRequest(ServerContextContainAble context) {
        try {
            Optional.ofNullable(handlers.get(context.getRequest().getClass())).orElseThrow(() ->
                    new IllegalManagerArgumentException("RequestHandlerManager contains illegal argument")).handleRequestContent(context);
        } catch (IllegalManagerArgumentException e) {
            e.printStackTrace();
        }
    }
}