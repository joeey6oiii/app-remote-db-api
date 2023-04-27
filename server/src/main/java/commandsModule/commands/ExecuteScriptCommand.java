package commandsModule.commands;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

// currently out of order
public class ExecuteScriptCommand implements ParameterizedCommand {
    private String response;
    private String[] args;
    public static LinkedList<String> historyOfDangerScript = new LinkedList<>();

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getResponse() {
        return this.response;
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
    public String describe() {
        return "Reads and executes a script from the specified file";
    }

    // реализация на клиенте
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
//                handler.execute(t);
            }
        } catch (IOException e) {
//            System.out.println("Incorrect script");
        }
        historyOfDangerScript.clear();
    }

}
