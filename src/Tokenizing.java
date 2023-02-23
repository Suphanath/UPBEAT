import java.util.NoSuchElementException;

public class Tokenizing implements Tokenizer {
    private int current;
    private String next;
    private final String src;
    public Tokenizing(String src){
        this.src = src;
        current = 0;
        computeNext();
    }

    @Override
    public boolean hasNextToken() {
        return next != null;
    }

    @Override
    public boolean peek(String s) {
        return peek().equals(s);
    }

    @Override
    public void consume(String s) throws SyntaxError {
        if(peek(s))
            consume();
        else
            throw new SyntaxError(s + " expected");
    }

    @Override
    public String peek() throws NoSuchElementException {
        if(hasNextToken()) return next;
        throw new NoSuchElementException("there are no more tokens");
    }

    @Override
    public String consume() throws NoSuchElementException {
        if(hasNextToken()) {
            String rs = next;
            computeNext();
            return rs;
        }
        throw new NoSuchElementException("there are no more tokens");
    }

    private void computeNext() throws IllegalArgumentException{
        StringBuilder s = new StringBuilder();
        while (current < src.length() && Character.isWhitespace(src.charAt(current))) current++;
        if(current == src.length()) {
            next = null;
            return;
        }
        char a = src.charAt(current);
        if(Character.isDigit(a)){
            s.append(a);
            for(current++; current < src.length() && Character.isDigit(src.charAt(current)); current++) {
                s.append(src.charAt(current));
            }
        }else if(a == '+' || a == '-' || a == '*' || a == '/' || a == '%' || a == '(' || a == ')'){
            s.append(a);
            current++;
        }else if(Character.isAlphabetic(a)){
            s.append(a);
            for(current++; current < src.length() && Character.isAlphabetic(src.charAt(current)); current++) {
                s.append(src.charAt(current));
            }
        }else throw new IllegalArgumentException(a + "is an illegal character");
        next = s.toString();
    }
}
