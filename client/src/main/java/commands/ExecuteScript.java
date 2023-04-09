package commands;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

/**
 * A class that implements the execute_script command, which allows you to execute a script
 *
 * @author Dmitrii Chebanenko
 */
public class ExecuteScript extends BaseCommand {
    /**
     * A field for storing scripts that can cause a loop when executing a command
     */
    public static LinkedList<String> historyOfDangerScript = new LinkedList<>();

    /**
     * Executes the script path to which is specified in the parameter field of the BaseCommand class
     *
     * @throws IOException
     */
    public void execute() throws IOException {
        if (historyOfDangerScript.contains(super.getParameter())) {
            System.out.println("Loop in script");
            return;
        }
        File file = new File(super.getParameter());
        try (InputStream in = new FileInputStream(file)) {
            String contents = IOUtils.toString(in, StandardCharsets.UTF_8);
            var a = contents.split("\n");
            for (var t : a) {
                if (t.split(" ")[0].equals("execute_script")) {
                    historyOfDangerScript.add(super.getParameter());
                }
                CommandHandler.handleCommand(t);
            }
        } catch (IOException e) {
            System.out.println("Incorrect script");
        }
        historyOfDangerScript.clear();
    }

    /**
     * Method that returns the description of the command.
     */
    public String describe() {
        return "Reads and executes a script from the specified file";
    }
}
