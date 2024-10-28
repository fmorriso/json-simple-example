import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;

public class Main {

    public static void main(String[] args) {
        System.out.format("Java version: %s%n", getJavaVersion());

        printSeparator();
        verifyPOJOtoJSON();
    }

    /**
     * Verify we can convert a (P)lain (O)ld (J)ava (O)bject to (J)ava (S)cript (O)bject (N)otation
     * using Google's GSON library.
     */
    private static void verifyPOJOtoJSON() {
        System.out.format("%n%s%n", getMethodName(1));
        final String filename = "patient.json";
        Gson g = getDefaultGson();
        // create a Patient instance from an external JSON text file
        try {
            File f = new File(filename);
            if (f.exists()) {
                FileReader fr = new FileReader(f);
                Patient p = g.fromJson(fr, Patient.class);
                System.out.println(p);
            } else {
                String msg = String.format("File %s not found! Expected at %s.%n", filename, f.getAbsolutePath());
                System.err.println(msg);
                throw new FileNotFoundException(msg);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        printSeparator();
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