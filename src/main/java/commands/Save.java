package commands;

import dataBase.DataBase;
import yamlsTools.GlobalPath;
import yamlsTools.YamlWriter;

import java.io.IOException;

/**
 * The class that implements the save command - saves the collection to a file along the path "src/main/resources/orderOutput.yaml"
 *
 * @author Dmitrii Chebanenko
 */
public class Save extends BaseCommand {
    /**
     * Method that saves Person elements from the DataBase to a file along the path "src/main/resources/orderOutput.yaml"
     *
     * @param obj - link to the database containing the collection
     */
    public void execute(DataBase obj) {
        YamlWriter yamlWriter = new YamlWriter();
        try {
            yamlWriter.writeYaml(obj.getCollection(), "orderOutput.yaml");
        } catch (IOException e) {
            System.out.println("Something went wrong, the file was not created, please, try again");
        }
        System.out.println("The collection has been saved. The path to the file is: \"" + GlobalPath.getPath() + "orderOutput.yaml\"");
    }

    /**
     * Method that displays the description of the command
     */
    public void describe() {
        System.out.println("Saves the collection to a file");
    }
}
