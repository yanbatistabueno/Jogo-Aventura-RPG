import java.io.InputStream;
import java.util.Scanner;

public class EnterAwareScanner {
    private final Scanner scanner;
    private boolean enterPressed;

    public EnterAwareScanner(InputStream in) {
        this.scanner = new Scanner(in);
        this.enterPressed = false;
    }
    public String nextLine() {
        String line = scanner.nextLine();
        enterPressed = line.isEmpty();
        return line;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    public int nextIntOrDefault(int defaultValue) {
        String line = nextLine();
        if (enterPressed) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public void close() {
        scanner.close();
    }
}
