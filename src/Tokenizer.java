import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;
public class Tokenizer {
    private static final Pattern SPLIT_PATTERN = Pattern.compile("([\\s]++)|(?<=[=+\\-*/%(){}^])|(?=[=+\\-*/%(){}^])");
    private final Queue<String> tkz;
    public Tokenizer(String txt) {
        tkz = new LinkedList<>();
        String[] split = SPLIT_PATTERN.split(txt);
        for (String s : split) {
            if (!s.trim().isEmpty()) {
                tkz.add(s);
            }
        }
    }

    public String peek() throws SyntaxError {
        if (tkz.isEmpty()) {
            throw new SyntaxError("Empty");
        }
        return tkz.element();
    }

    public boolean peek(String s) throws SyntaxError {
        return !tkz.isEmpty() && tkz.element().equals(s);
    }

    public String consume() {
        return tkz.remove();
    }

    public void consume(String s) throws SyntaxError {
        if (!peek(s)) {
            throw new SyntaxError("SyntaxError");
        }
        consume();
    }

    public boolean hasNextToken() {
        return !tkz.isEmpty();
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


