import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final String source;
    private final List<Token> tokens;
    private int start = 0;
    private int current = 0;
    private int line = 1;

    public Tokenizer(String source) {
        this.source = source;
        this.tokens = new ArrayList<>();
    }

    public List<Token> tokenize() {
        while (!isAtEnd()) {
            start = current;
            char c = advance();
            switch (c) {
                case '(' -> addToken(TokenType.LEFT_PAREN);
                case ')' -> addToken(TokenType.RIGHT_PAREN);
                case '{' -> addToken(TokenType.LEFT_BRACE);
                case '}' -> addToken(TokenType.RIGHT_BRACE);
                case '+' -> addToken(TokenType.PLUS);
                case '-' -> addToken(TokenType.MINUS);
                case '*' -> addToken(TokenType.STAR);
                case '/' -> addToken(match('/') ? comment().getType() : TokenType.SLASH);
                case '%' -> addToken(TokenType.PERCENT);
                case '^' -> addToken(TokenType.CARET);
                case '=' -> addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL);
                case '<' -> addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
                case '>' -> addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
                case ';' -> addToken(TokenType.SEMICOLON);
                case ' ', '\r', '\t' -> {} // Ignore whitespace
                case '\n' -> line++;
                case '"' -> string();
                default -> {
                    if (isDigit(c)) {
                        number();
                    } else if (isAlpha(c)) {
                        identifier();
                    } else {
                        throw error(line, "Unexpected character.");
                    }
                }
            }
        }

        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private char advance() {
        current++;
        return source.charAt(current - 1);
    }

    private boolean match(char expected) {
        if (isAtEnd()) {
            return false;
        }
        if (source.charAt(current) != expected) {
            return false;
        }
        current++;
        return true;
    }

    private Token comment() {
        while (peek() != '\n' && !isAtEnd()) {
            advance();
        }
        return new Token(TokenType.COMMENT, "", null, line);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String lexeme = source.substring(start, current);
        tokens.add(new Token(type, lexeme, literal, line));
    }

    private char peek() {
        if (isAtEnd()) {
            return '\0';
        }
        return source.charAt(current);
    }

    private char peekNext() {
        if (current + 1 >= source.length()) {
            return '\0';
        }
        return source.charAt(current + 1);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private void number() {
        while (isDigit(peek())) {
            advance();
        }
        if (peek() == '.' && isDigit(peekNext())) {
            advance();
            while (isDigit(peek())) {
                advance();
            }
        }
        addToken(TokenType.NUMBER, Long.parseLong(source.substring(start, current)));
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    private void identifier() {
        while (isAlphaNumeric(peek())) {
                advance();
        }
        String text = source.substring(start, current);
        TokenType type = TokenType.IDENTIFIER;
        switch (text) {
            case "collect" -> type = TokenType.COLLECT;
            case "done" -> type = TokenType.DONE;
            case "down" -> type = TokenType.DOWN;
            case "downleft" -> type = TokenType.DOWNLEFT;
            case "downright" -> type = TokenType.DOWNRIGHT;
            case "else" -> type = TokenType.ELSE;
            case "if" -> type = TokenType.IF;
            case "invest" -> type = TokenType.INVEST;
            case "move" -> type = TokenType.MOVE;
            case "nearby" -> type = TokenType.NEARBY;
            case "opponent" -> type = TokenType.OPPONENT;
            case "relocate" -> type = TokenType.RELOCATE;
            case "shoot" -> type = TokenType.SHOOT;
            case "then" -> type = TokenType.THEN;
            case "up" -> type = TokenType.UP;
            case "upleft" -> type = TokenType.UPLEFT;
            case "upright" -> type = TokenType.UPRIGHT;
            case "while" -> type = TokenType.WHILE;
        }
        addToken(type);
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') {
                line++;
            }
            advance();
        }
        if (isAtEnd()) {
            throw error(line, "Unterminated string.");
        }
        // The closing ".
        advance();
        String value = source.substring(start + 1, current - 1);
        addToken(TokenType.STRING, value);
        }

        private boolean isAlphaNumeric(char c) {
            return isAlpha(c) || isDigit(c);
        }

        private RuntimeException error(int line, String message) {
            return new RuntimeException("line " + line + ": " + message);
        }
    }
