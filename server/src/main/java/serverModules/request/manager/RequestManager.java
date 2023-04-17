package serverModules.request.manager;

import requests.CommandDescriptionsRequest;
import requests.ExecutionResultRequest;
import requests.Request;
import serverModules.processors.CommandDescriptionsProcessor;
import serverModules.processors.CommandProcessor;
import serverModules.processors.Processor;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Optional;

public class RequestManager implements RequestManageAble {
    private final LinkedHashMap<Class<? extends Request>, Processor> processors;

    {
        processors = new LinkedHashMap<>();

        processors.put(CommandDescriptionsRequest.class, new CommandDescriptionsProcessor());
        processors.put(ExecutionResultRequest.class, new CommandProcessor());
    }

    @Override
    public void manageRequest(Request request) {
        try {
            Optional.ofNullable(processors.get(request.getClass())).orElseThrow(IOException::new).proceed(request); // тут по хорошему свою ошибочку написать бы
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
