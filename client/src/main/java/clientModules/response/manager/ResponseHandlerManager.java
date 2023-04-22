package clientModules.response.manager;

import clientModules.response.responseContentHandlers.CommandDescriptionsRCH;
import clientModules.response.responseContentHandlers.ResponseContentHandler;
import clientModules.response.responseContentHandlers.ResponseOutputHandler;
import exceptions.IllegalManagerArgumentException;
import responses.CommandDescriptionsResponse;
import responses.CommandExecutionResultResponse;
import responses.Response;

import java.util.LinkedHashMap;
import java.util.Optional;

public class ResponseHandlerManager {
    private final LinkedHashMap<Class<? extends Response>, ResponseContentHandler> handlers;

    {
        handlers = new LinkedHashMap<>();

        handlers.put(CommandDescriptionsResponse.class, new CommandDescriptionsRCH());
        handlers.put(CommandExecutionResultResponse.class, new ResponseOutputHandler());
    }

    public void manageResponse(Response response) {
        try {
            Optional.ofNullable(handlers.get(response.getClass())).orElseThrow(() ->
                    new IllegalManagerArgumentException("ResponseHandlerManager contains illegal argument")).handleResponseContent(response);
        } catch (IllegalManagerArgumentException e) {
            e.printStackTrace();
        }
    }
}
