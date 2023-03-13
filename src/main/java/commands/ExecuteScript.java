package commands;

import org.apache.commons.io.IOUtils;

import dataBase.DataBase;

import java.io.*;
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
     * @param obj - link to the database containing the collection
     * @throws IOException
     */
    public void execute(DataBase obj) throws IOException {
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
     * Method that displays the description of the command
     */
    public void describe() {
        System.out.println("Reads and executes a script from the specified file");
    }
}
