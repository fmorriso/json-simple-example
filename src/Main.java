import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//
import java.time.ZonedDateTime;
import java.util.Optional;
//


public class Main {

    public static void main(String[] args) throws IOException {
        System.out.format("Java version: %s%n", getJavaVersion());

        printSeparator();
        verifyPOJOtoJSON();
    }

    /**
     * Verify we can convert a (P)lain (O)ld (J)ava (O)bject to (J)ava (S)cript (O)bject (N)otation
     * using Google's GSON library.
     */
    private static void verifyPOJOtoJSON() throws IOException {
        System.out.format("%n%s%n", getMethodName(1));

        Path currentDir = Paths.get("").toAbsolutePath();
        System.out.format("Current working directory: %s%n",currentDir);

        final String targetFileName = "patient.json";
        final String basePath = ".";
        final Path baseDir = Paths.get(".");

        Optional<Path> result = Optional.ofNullable(findPathFirst(baseDir, targetFileName));
        Path foundFile = null;
        if(result.isPresent()) {
            foundFile = result.get().toAbsolutePath();
        }

        Gson g = getDefaultGson();
        // create a Patient instance from an external JSON text file
        try {
            File f = new File(foundFile.toRealPath().toString());
            if (f.exists()) {
                FileReader fr = new FileReader(f);
                Patient p = g.fromJson(fr, Patient.class);
                System.out.println(p);
            } else {
                String msg = String.format("File %s not found! Expected at %s.%n", targetFileName, f.getAbsolutePath());
                System.err.println(msg);
                throw new FileNotFoundException(msg);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        printSeparator();
    }


    /** Find the first occurrence of the path to a file from a base directory.
     * @param base - the base directory path from where the search begins.
     * @param targetFileName - the target file name to look for.
     * @return - Path to where the file was found or null if not found.
     * @throws IOException - the exception thrown if anything goes wrong during the search.
     */
    public static Path findPathFirst(Path base, String targetFileName) throws IOException {
        final Path[] result = {null}; // To store the result as a shared variable

        Files.walkFileTree(base, new FileUtils(targetFileName, result));

        return result[0]; // Return the found file, or null if not found
    }

    private static void printSeparator() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            sb.append("=");
        }
        System.out.println(sb);
    }

    /**
     * @return an instance of Gson that is customized to handle ZonedDateTime fields.
     */
    private static Gson getDefaultGson() {
        ZonedDateTimeTypeAdapter customAdapter = new ZonedDateTimeTypeAdapter();
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(ZonedDateTime.class, customAdapter).create();
    }


    /**
     * get the java version that is running the current program
     *
     * @return string containing the java version running the current program
     */
    private static String getJavaVersion() {
        Runtime.Version rtv = Runtime.version();
        return String.format("%s.%s.%s.%s", rtv.feature(), rtv.interim(), rtv.update(), rtv.patch());
    }

    public static String getMethodName(final int depth) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

        //System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
        // return ste[ste.length - depth].getMethodName();  //Wrong, fails for depth = 0
        return ste[ste.length - 1 - depth].getMethodName(); //Thank you Tom Tresansky
    }



}