import java.util.NoSuchElementException;

public interface Tokenizer {
    boolean hasNextToken();
    String peek() throws NoSuchElementException;
    String consume() throws NoSuchElementException;
    boolean peek(String s);
    void consume(String s);
    public static boolean isNumber(String peek){
        try {
            Double.parseDouble(peek);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    };
}

