package serverModules.request.manager;

import exceptions.IllegalManagerArgumentException;
import requests.CommandDescriptionsRequest;
import requests.CommandExecutionResultRequest;
import requests.Request;
import serverModules.request.requestContentHandlers.CommandDescriptionsRCH;
import serverModules.request.requestContentHandlers.CommandExecutionRCH;
import serverModules.request.requestContentHandlers.RequestContentHandler;

import java.util.LinkedHashMap;
import java.util.Optional;

public class RequestHandlerManager {
    private final LinkedHashMap<Class<? extends Request>, RequestContentHandler> handlers;

    {
        handlers = new LinkedHashMap<>();

        handlers.put(CommandDescriptionsRequest.class, new CommandDescriptionsRCH());
        handlers.put(CommandExecutionResultRequest.class, new CommandExecutionRCH());
    }

    public void manageRequest(Request request) {
        try {
            Optional.ofNullable(handlers.get(request.getClass())).orElseThrow(() ->
                    new IllegalManagerArgumentException("Request Handler Manager contains illegal argument")).handleRequestContent(request);
        } catch (IllegalManagerArgumentException e) {
            e.printStackTrace();
        }
    }
}
