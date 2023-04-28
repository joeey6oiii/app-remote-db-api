package fileService;

import fileService.YAMLTools.YAMLReader;
import fileService.YAMLTools.YAMLWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileService {

    public <T> List<T> readFile(File file, Class<T> type) throws IOException {
        return new YAMLReader().read(file, type);
    }

    public void writeObjectToFile(File file, Object obj) throws IOException {
        if (!file.exists()) {
            this.createFile(file);
        }
        new YAMLWriter().writeYAML(obj, file);
    }

    public void createFile(File file) throws IOException {
        if (!file.createNewFile()) {
            throw new IOException("Unable to create file " + file.getPath());
        }
    }

}