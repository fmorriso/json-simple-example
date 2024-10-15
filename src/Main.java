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

    private static void verifyPOJOtoJSON() {
        System.out.println("verifyPOJOtoJSON");
        Gson g = getDefaultGson();
        try {
            File f = new File("patient.json");
            FileReader fr = new FileReader(f);
            Patient p = g.fromJson(fr, Patient.class);
            System.out.println(p);
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

    private static Gson getDefaultGson(){
        return new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(ZonedDateTime.class, new TypeAdapter<ZonedDateTime>() {
                    @Override
                    public void write(JsonWriter out, ZonedDateTime value) throws IOException {
                        out.value(value.toString());
                    }

                    @Override
                    public ZonedDateTime read(JsonReader in) throws IOException {
                        return ZonedDateTime.parse(in.nextString());
                    }
                }).create();
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


}