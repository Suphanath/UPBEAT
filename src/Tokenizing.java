import java.util.NoSuchElementException;

public class Tokenizing extends Tokenizer {
    private int current;
    private String next;
    private String src;

    public Tokenizing(String txt) {
        super(txt);
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
        throw new NoSuchElementException("it's empty");
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

    private void comment(){
        while (current < src.length() && src.charAt(current)!='\n'){
            current++;
        }
    }

    private void computeNext() throws IllegalArgumentException{
        StringBuilder sb = new StringBuilder();
        while (current < src.length() && Character.isWhitespace(src.charAt(current))) {
            if(src.charAt(current)=='#'){
                comment();
            }else{
                current++;
            }
        }
        if(current == src.length()) {
            next = null;
            return;
        }
        char a = src.charAt(current);
        if(Character.isDigit(a)){
            sb.append(a);
            for(current++; current < src.length() && Character.isDigit(src.charAt(current)); current++) {
                sb.append(src.charAt(current));
            }
        }else if(a == '_') {
            sb.append(a);
            current++;
        }else if("()+-*/%^{}=".contains(String.valueOf(a))){
            sb.append(a);
            current++;
        }else if(Character.isAlphabetic(a)){
            sb.append(a);
            for(current++; current < src.length() && Character.isAlphabetic(src.charAt(current)); current++) {
                sb.append(src.charAt(current));
            }
        }else throw new IllegalArgumentException(a + "is an illegal character");
        next = sb.toString();
    }
}
