package fileService;

import fileService.YAMLTools.YAMLReader;
import fileService.YAMLTools.YAMLWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * A class for working with a file.
 */

public class FileService {

    /**
     * Reads the specified file using {@link YAMLReader}.
     *
     * @param file file to read
     * @param type type of the objects to create
     * @param <T> arbitrary non-primitive data type
     * @return list with elements of type T or empty <code>ArrayList</code>
     * @throws IOException if failed during I/O operations
     */

    public <T> List<T> readFile(File file, Class<T> type) throws IOException {
        return new YAMLReader().read(file, type);
    }

    /**
     * Writes the specified objects to the specified file using {@link YAMLWriter}.
     *
     * @param file file to write to
     * @param obj object to write
     * @throws IOException if failed during I/O operations
     */

    public void writeObjectToFile(File file, Object obj) throws IOException {
        if (!file.exists()) {
            this.createFile(file);
        }
        new YAMLWriter().writeYAML(obj, file);
    }

    /**
     * Creates new file.
     *
     * @param file file to create
     * @throws IOException if failed during I/O operations
     */

    public void createFile(File file) throws IOException {
        if (!file.createNewFile()) {
            throw new IOException("Unable to create file " + file.getPath());
        }
    }

}