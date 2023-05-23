package clientModules.response.handlers;

import commands.CommandDescription;
import commandsModule.ClientCommandsKeeper;
import responses.ClientCommandsResponse;
import serializer.ObjectSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class that works with the commands' response.
 */

public class ClientCommandsHandler implements ResponseHandler<ClientCommandsResponse> {

    /**
     * A method that handles the commands' responses and calls the
     * {@link ClientCommandsKeeper#setCommands(List)} method.
     *
     * @param responses the received responses map
     */

    @Override
    public void handleResponses(HashMap<Integer, ClientCommandsResponse> responses) throws IOException {
        if (responses.isEmpty() || responses.get(-1) == null) {
            throw new IllegalArgumentException("Responses map is empty");
        }
        byte[] serializedList = new byte[responses.get(-1).getSerializedListSize()];
        int totalResponsesAmount = responses.get(-1).getTotalResponsesAmount();
        byte[] lastResponsePartOfList = responses.get(-1).getPartOfList();
        int currentIndex = 0;

        for (int i = 1; i <= totalResponsesAmount - 1; i++) {
            byte[] partOfList = responses.get(i).getPartOfList();
            System.arraycopy(partOfList, 0, serializedList, currentIndex, partOfList.length);
            currentIndex += partOfList.length;
        }
        System.arraycopy(lastResponsePartOfList, 0, serializedList, currentIndex, lastResponsePartOfList.length);

        try {
            Object obj = new ObjectSerializer().deserialize(serializedList);
            if (obj instanceof List<?>) {
                List<?> deserializedList = (List<?>) obj;
                List<CommandDescription> list = new ArrayList<>();

                for (Object element : deserializedList) {
                    if (element instanceof CommandDescription) {
                        list.add((CommandDescription) element);
                    } else {
                        throw new IOException("Unexpected error: Received object of non-CommandDescription type while trying to receive commands list");
                    }
                }

                ClientCommandsKeeper.setCommands(list);
            } else {
                throw new IOException("Unexpected error: Received object of non-List type while trying to receive commands list");
            }
        } catch (ClassNotFoundException e) {
            throw new IOException("Unexpected error: could not find class");
        }
    }

}