package hydro.pi.bridge.common.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

/**
 * Simple file reader for managing data from files.
 * 
 * @author Sam Butler
 * @since September 5, 2022
 */
public class FileReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);
    private static final String LOCAL_ENV_YML = "env.local.yml";

    /**
     * Will read the YML file of the passed in file name. It will throw an exception
     * if the file is not of type yml extension.
     * 
     * @param filePath The file to process.
     * @return HashMap of the data.
     */
    public static HashMap<String, String> readYML(String filePath) {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = new FileInputStream(filePath)) {
            return yaml.load(inputStream);
        } catch (IOException e) {
            LOGGER.warn("Could not process YML file '{}'", filePath);
            return null;
        }
    }

    /**
     * For development use to pull user local credentials from the env.loca.yml file
     * location in the resources class path.
     * 
     * @return The hash map of the local credentials.
     */
    public static HashMap<String, String> readLocalEnvironment() {
        return readYML(String.format("src/main/resources/%s", LOCAL_ENV_YML));
    }
}
