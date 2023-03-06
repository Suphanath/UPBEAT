import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;
public class Tokenizer {

    private static final Pattern SPLIT_PATTERN = Pattern.compile("([\\s]++)|(?<=[=+\\-*/%(){}^])|(?=[=+\\-*/%(){}^])");

    private final Queue<String> tokens;

    public Tokenizer(String txt) {
        tokens = new LinkedList<>();
        String[] separated = SPLIT_PATTERN.split(txt);
        for (String s : separated) {
            if (!s.trim().isEmpty()) {
                tokens.add(s);
            }
        }
    }

    public String peek() throws SyntaxError {
        if (tokens.isEmpty()) {
            throw new SyntaxError("Empty");
        }
        return tokens.element();
    }

    public boolean peek(String s) throws SyntaxError {
        return !tokens.isEmpty() && tokens.element().equals(s);
    }

    public String consume() {
        return tokens.remove();
    }

    public void consume(String s) throws SyntaxError {
        if (!peek(s)) {
            throw new SyntaxError("Error");
        }
        consume();
    }

    public boolean hasNextToken() {
        return tokens.isEmpty();
    }

    public static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


