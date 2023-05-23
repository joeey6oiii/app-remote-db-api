package serverModules.request.handlers;

import commandsModule.ClientCommandsKeeper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.ClientCommandsResponse;
import serializer.ObjectSerializer;
import serverModules.context.ServerContext;
import serverModules.response.sender.ClientCommandsResponseSender;

import java.util.Arrays;
import java.io.IOException;

/**
 * A class that works with the client commands request.
 */

public class ClientCommandsHandler implements RequestHandler {
    private static final Logger logger = LogManager.getLogger("logger.ClientCommandsHandler");

    /**
     * A method that handles the client commands request and sends the client commands response.
     *
     * @param context the specified server settings
     */

    @Override
    public void handleRequest(ServerContext context) {
        final int PACKET_SIZE = 400;
        try {
            byte[] data = new ObjectSerializer().serialize(ClientCommandsKeeper.getCommands());

            int totalResponsesAmount = (int) Math.ceil((double) data.length / PACKET_SIZE);
            int serializedListSize = data.length;

            for (int i = 0; i < totalResponsesAmount; i++) {
                int startIndex = i * PACKET_SIZE;
                int endIndex = Math.min(startIndex + PACKET_SIZE, data.length);
                byte[] partOfData = Arrays.copyOfRange(data, startIndex, endIndex);

                int currentResponseNumber = (i == totalResponsesAmount - 1) ? -1 : (i + 1);

                ClientCommandsResponse response = new ClientCommandsResponse(partOfData, serializedListSize, currentResponseNumber, totalResponsesAmount);
                new ClientCommandsResponseSender().sendResponse(context.getConnectionModule(), context.getCallerBack(), response);
            }
        } catch (IOException e) {
            logger.error("Unexpected error while processing commands request");
        }
    }

}
