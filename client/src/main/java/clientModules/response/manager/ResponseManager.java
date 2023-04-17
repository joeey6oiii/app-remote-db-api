package clientModules.response.manager;

import responses.CommandDescriptionsResponse;
import responses.ExecutionResultResponse;
import responses.Response;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Optional;

public class ResponseManager implements ResponseManageAble {
    private final LinkedHashMap<Class<? extends Response>, ResponseManageAble> managers;

    {
        managers = new LinkedHashMap<>();

//        managers.put(CommandDescriptionsResponse.class, new [INSERT_MANAGER_CLASS]);
        managers.put(ExecutionResultResponse.class, new ResponseOutputManager());
    }

    @Override
    public void manageResponse(Response response) {
        try {
            Optional.ofNullable(managers.get(response.getClass())).orElseThrow(IOException::new).manageResponse(response); // тут по хорошему свою ошибочку написать бы
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
