package serverModules.processors;

import commands.CommandHandler;
import requests.Request;

import java.util.ArrayList;
import java.util.List;

public class CommandDescriptionsProcessor implements Processor {

    @Override
    public void proceed(Request request) {
        List<String> keys = new ArrayList<>(CommandHandler.getMap().keySet());
        for (String key : keys) {
            var value = CommandHandler.getMap().get(key).getCommandDescriptionObj();
        }
    }
}
