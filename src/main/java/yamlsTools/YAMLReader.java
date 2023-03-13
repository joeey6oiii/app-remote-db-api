package yamlsTools;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import commands.*;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A class for reading YAML files.
 * <p>
 * YAMLReader uses {@link ObjectMapper} with {@link YAMLFactory} parameter to create objects from the YAML file. Uses custom
 * {@link DeserializationFeature} configuration: reads enums using <code>toString()</code>; accepts: empty array as null object,
 * single value as array, empty string as null object; doesn't fail on: unknown properties, ignored properties, invalid subtype,
 * missing creator properties, null creator properties and null for primitives.
 */

public class YAMLReader {
    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory())
            .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true)
            .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false)
            .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
            .findAndRegisterModules();

    /**
     * Finds and reads the YAML file at the specified path. Creates object(s) of the specified class and adds them to the <code>List</code>.
     *
     * @param path the specified full path to the YAML file
     * @param type the specified class whose objects will be created in the method
     * @param <T> arbitrary non-primitive data type
     * @return list of created elements of type T
     */

    public <T> List<T> read(String path, Class<T> type) {
        Class<T[]> arrayClass = null;
        T[] objects = null;
        try {
            arrayClass = (Class<T[]>) Class.forName("[L" + type.getName() + ";");
        } catch (ClassNotFoundException ignored) {}
        try {
            System.out.print("\n");
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
            objects = objectMapper.readValue(inputStreamReader, arrayClass);
            inputStreamReader.close();
        } catch (Exception e) {
            boolean isPath = false;
            Scanner scanner = new Scanner(System.in);
            System.out.print("Invalid path or the file is damaged: \u001B[31m" + e.getMessage() + "\u001B[0m\n");
            do {
                try {
                    System.out.print("\nEnter path to continue program execution or \"exit\" to terminate the program\n$ ");
                    path = scanner.nextLine();
                    if (path.equalsIgnoreCase("EXIT")) {
                        System.out.print("\n");
                        new Exit().execute(null);
                    } else {
                        System.out.print("\n");
                        GlobalPath.setPath(path);
                        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
                        objects = objectMapper.readValue(inputStreamReader, arrayClass);
                        inputStreamReader.close();
                        isPath = true;
                    }
                } catch (Exception exception) {
                    System.out.print("Invalid path or the file is damaged: \u001B[31m" + exception.getMessage() + "\u001B[0m\n");
                }
            } while (!isPath);
        }
        return Arrays.asList(objects);
    }
}