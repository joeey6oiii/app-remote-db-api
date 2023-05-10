package serverModules.response.sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.Response;
import serializer.ObjectSerializer;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;

public class ResponseSender implements ResponseAble<Response> {
    private static final Logger logger = LogManager.getLogger("logger.ResponseSender");

    @Override
    public void sendResponse(ConnectionModule module, CallerBack callerBack, Response response) {
        if (response != null) {
            ObjectSerializer os = new ObjectSerializer();
            try {
                module.sendData(os.serialize(response), callerBack.getAddress(), callerBack.getPort());
            } catch (Exception e) {
                logger.fatal("Something went wrong during I/O operations", e);
            }
        }
    }
}
