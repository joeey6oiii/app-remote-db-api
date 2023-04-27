package YAMLTools;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * A class that implements the save command, it saves the collection to a file in the specified path.
 *
 * @author Dmitrii Chebanenko
 */
public class YAMLWriter {
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory()).findAndRegisterModules();

    public void writeYAML(Object object, String file) throws IOException {
        File newFile = new File("server\\src\\main\\resources\\" + file);
        newFile.createNewFile();
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.mapper.writeValue(newFile, object);
    }
}