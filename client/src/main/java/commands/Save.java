package commands;

import dataBase.GlobalObj;
import yamlsTools.GlobalPath;
import yamlsTools.YAMLWriter;

import java.io.IOException;

/**
 * The class that implements the save command - saves the collection to a file along the path "src/main/resources/orderOutput.yaml"
 *
 * @author Dmitrii Chebanenko
 */
public class Save extends BaseCommand {
    /**
     * Method that saves Person elements from the DataBase to the "orderOutput.yaml" file along the specified path
     */
    public void execute() {
        YAMLWriter yamlWriter = new YAMLWriter();
        try {
            yamlWriter.writeYAML(GlobalObj.dataBase.getCollection(), "orderOutput.yaml");
        } catch (IOException e) {
            System.out.println("Something went wrong, the file was not created, please, try again");
        }
        System.out.println("The collection has been saved. The path to the file is: \"" + GlobalPath.getPath() + "orderOutput.yaml\"");
    }

    /**
     * Method that returns the description of the command.
     */
    public String describe() {
        return "Saves the collection to a file";
    }
}
