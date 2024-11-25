import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils extends SimpleFileVisitor<Path> {
    private final String targetFileName;
    private final Path[] result;

    public FileUtils(String targetFileName, Path[] result) {
        this.targetFileName = targetFileName;
        this.result = result;
    }

    @Override
    public FileVisitResult visitFile(Path p, BasicFileAttributes attrs) {
        if (p.getFileName().toString().equals(targetFileName)) {
            result[0] = p; // Store the found path
            return FileVisitResult.TERMINATE; // Stop traversal once found
        }
        return FileVisitResult.CONTINUE; // Continue if not found
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE; // Skip unreadable files
    }
}