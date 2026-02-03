import java.io.IOException;
import java.nio.file.*;

public class EightBall {

    private static final Path BASE_DIR = Paths.get("resources").toAbsolutePath().normalize();
    private static final String[] ALLOWED_FILES = {
            "0.txt",
            "1.txt",
            "2.txt"
    };

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No input provided.");
            return;
        }

        int index;

        try {
            index = Integer.parseInt(args[0]) % ALLOWED_FILES.length;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return;
        }

        Path safePath = BASE_DIR.resolve(ALLOWED_FILES[index]).normalize();

        // Ensure the path stays inside BASE_DIR
        if (!safePath.startsWith(BASE_DIR)) {
            System.out.println("Invalid file path.");
            return;
        }

        try {
            Files.lines(safePath).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}
