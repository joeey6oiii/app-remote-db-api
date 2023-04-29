package commandsModule.commands;

import commandsModule.handler.CommandHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HistoryCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("logger.HistoryCommand");
    private String response;
    private final Map<String, BaseCommand> commands;

    public HistoryCommand(Map<String, BaseCommand> commands) {
        this.commands = commands;
    }

    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String getResponse() {
        return this.response;
    }

    @Override
    public String describe() {
        return "Outputs last input 9 commands without arguments";
    }

    @Override
    public void execute() throws IOException {
        List<BaseCommand> list = CommandHandler.getHistory();
        try {
            if (list.isEmpty()) {
                this.response = "No command history yet";
            } else {
                List<String> history = list.stream()
                        .map(BaseCommand::getName)
                        .collect(Collectors.toList());
                int i = 9;
                if (history.size() > i) {
                    history = history.subList(history.size() - i, history.size());
                }
                this.response = history.toString();
            }
            logger.info("Executed HistoryCommand");
        } catch (Exception e) {
            this.response = "Something went wrong during history command execution...";
            logger.warn("HistoryCommand was not executed");
        }
    }
}
