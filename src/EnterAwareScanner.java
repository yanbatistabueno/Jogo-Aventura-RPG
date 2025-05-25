import java.io.InputStream;
import java.util.Scanner;

/**
 * Scanner que detecta se o usuário pressionou apenas Enter (linha vazia).
 */
public class EnterAwareScanner {
    private final Scanner scanner;
    private boolean enterPressed;

    /**
     * Construtor principal.
     * @param in o InputStream (normalmente System.in)
     */
    public EnterAwareScanner(InputStream in) {
        this.scanner = new Scanner(in);
        this.enterPressed = false;
    }

    /**
     * Lê a próxima linha e marca se foi apenas Enter.
     * @return a linha lida ("" se foi apenas Enter)
     */
    public String nextLine() {
        String line = scanner.nextLine();
        enterPressed = line.isEmpty();
        return line;
    }

    /**
     * @return true se a última chamada a nextLine() retornou linha vazia (Enter)
     */
    public boolean isEnterPressed() {
        return enterPressed;
    }

    /**
     * Lê um inteiro da entrada. Se o usuário apenas apertar Enter, retorna null.
     * @return o Integer lido, ou null se foi apenas Enter ou número inválido
     */
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

    /**
     * Fecha o Scanner interno.
     */
    public void close() {
        scanner.close();
    }
}
