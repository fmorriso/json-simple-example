import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class FileFinder {
    public static Optional<Path> findFirstOccurrence(Path startDir, String fileName) {
        try (Stream<Path> pathStream = Files.walk(startDir)) {
            return pathStream
                    .filter(Files::isRegularFile) // Filter only regular files
                    .filter(path -> path.getFileName().toString().equals(fileName)) // Match file name
                    .findFirst(); // Return the first match as an Optional
        } catch (IOException e) {
            // Return empty if an exception occurs
            return Optional.empty();
        }

    }
}
