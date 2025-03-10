import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileUtilities {

    /** Find the first occurrance of a file somewhere within the specified starting directory
     * or one of its subdirectories.
     * @param targetFileName - the filename to find.
     * @param startingDir - the starting point of where to begin searching.
     * @return Path where the file was first found or null.
     * @throws IOException
     */
    public static Path findFile(String targetFileName, Path startingDir) throws IOException {
        Optional<Path> result = Optional.ofNullable(findPathFirst(startingDir, targetFileName));
        Path foundFile = null;
        if(result.isPresent()) {
            foundFile = result.get().toAbsolutePath();
        }
        return foundFile;
    }

    /** Find a file from a known starting directory
     * @param base - The starting path from which to begin searching.
     * @param targetFileName - filename
     * @return - Path to where the file was found or null if not found.
     * @throws IOException
     */
    public static Path findPathFirst(Path base, String targetFileName) throws IOException {
        final Path[] result = {null}; // To store the result as a shared variable

        Files.walkFileTree(base, new FileUtils(targetFileName, result));

        return result[0]; // Return the found file, or null if not found
    }


    /** Return the path to where the current Java program began execution.
     * @return Path instance
     */
    public static Path getCurrentDirectory() {
        String workingDir = System.getProperty("user.dir");
        return Path.of(workingDir);
    }


}
