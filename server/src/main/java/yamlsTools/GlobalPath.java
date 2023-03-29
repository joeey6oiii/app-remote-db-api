package yamlsTools;

/**
 * A class that allows the program to use the path to the directory based on the path to the file entered by the user.
 */

public class GlobalPath {
    private static String globalPath = "";

    /**
     * @return path to the directory
     */

    public static String getPath() {
        return globalPath;
    }

    /**
     * Sets the directory path based on the user-specified file path by removing the filename with the file extension from the path.
     *
     * @param path path to the file
     */

    public static void setPath(String path) {
        if (path.contains("/")) {
            globalPath = path.substring(0, path.lastIndexOf("/") + 1);
        } else if (path.contains("\\")) {
            globalPath = path.substring(0, path.lastIndexOf("\\") + 1);
        }
    }
}
