package serverModules.response.sender;

import responses.Response;
import serializer.ObjectSerializer;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;

public class ResponseSender implements ResponseAble<Response> {

    @Override
    public void sendResponse(ConnectionModule module, CallerBack callerBack, Response response) {
        ObjectSerializer os = new ObjectSerializer();
        try {
            module.sendData(os.serialize(response), callerBack.getAddress(), callerBack.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
