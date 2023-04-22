package serverModules.request.manager;

import exceptions.IllegalManagerArgumentException;
import requests.CommandDescriptionsRequest;
import requests.CommandExecutionResultRequest;
import requests.Request;
import serverModules.context.ServerContext;
import serverModules.request.contentHandlers.CommandDescriptionsRCH;
import serverModules.request.contentHandlers.CommandExecutionRCH;
import serverModules.request.contentHandlers.RequestContentHandler;

import java.util.LinkedHashMap;
import java.util.Optional;

public class RequestHandlerManager {
    private final LinkedHashMap<Class<? extends Request>, RequestContentHandler> handlers;

    {
        handlers = new LinkedHashMap<>();

        handlers.put(CommandDescriptionsRequest.class, new CommandDescriptionsRCH());
        handlers.put(CommandExecutionResultRequest.class, new CommandExecutionRCH());
    }

    public void manageRequest(ServerContext context) {
        try {
            Optional.ofNullable(handlers.get(context.getRequest().getClass())).orElseThrow(() ->
                    new IllegalManagerArgumentException("RequestHandlerManager contains illegal argument")).handleRequestContent(context);
        } catch (IllegalManagerArgumentException e) {
            e.printStackTrace();
        }
    }
}
