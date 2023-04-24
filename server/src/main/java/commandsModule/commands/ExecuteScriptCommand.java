package commandsModule.commands;

import commands.CommandDescription;
import commandsModule.master.CommandHandler;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class ExecuteScriptCommand implements BaseCommand, ParameterizedCommand {
    private final String name = "execute_script";
    private final CommandHandler handler;
    private String[] args;
    public static LinkedList<String> historyOfDangerScript = new LinkedList<>();

    public ExecuteScriptCommand(CommandHandler handler) {
        this.handler = handler;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String[] getArguments() {
        return this.args;
    }

    @Override
    public void setArguments(String[] args) {
        this.args = args;
    }

    @Override
    public void clearArguments() {
        this.args = new String[]{};
    }

    @Override
    public CommandDescription getCommandDescriptionObj() {
        return new CommandDescription(name);
    }

    @Override
    public String describe() {
        return "Reads and executes a script from the specified file";
    }

    @Override
    public void execute() throws IOException {
        if (historyOfDangerScript.contains(args[0])) {
            System.out.println("Script in loop"); // need fix
            return;
        }
        File file = new File(args[0]);
        try (InputStream in = new FileInputStream(file)) {
            String contents = IOUtils.toString(in, StandardCharsets.UTF_8);
            var a = contents.split("\n");
            for (var t : a) {
                if (t.split(" ")[0].equals("execute_script")) {
                    historyOfDangerScript.add(args[0]);
                }
                handler.handleCommand(t);
            }
        } catch (IOException e) {
            System.out.println("Incorrect script"); // need fix
        }
        historyOfDangerScript.clear();
    }

}
