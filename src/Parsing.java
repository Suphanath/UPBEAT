import java.util.List;
import java.util.ArrayList;

public class Parser {
    private List<Token> tokens;
    private int current;

    public Parser(String source) {
        Tokenizer tokenizer = new Tokenizer(source);
        this.tokens = tokenizer.tokenize();
        this.current = 0;
    }

    public List<Statement> parse() {
        List<Statement> statements = new ArrayList<>();
        while (!isAtEnd()) {
            statements.add(statement());
        }
        return statements;
    }

    private Statement statement() {
        if (match(TokenType.MOVE)) {
            return moveStatement();
        } else if (match(TokenType.DONE)) {
            return doneStatement();
        } else if (match(TokenType.LEFT_BRACE)) {
            return blockStatement();
        } else if (match(TokenType.IF)) {
            return ifStatement();
        } else if (match(TokenType.WHILE)) {
            return whileStatement();
        } else {
            return commandStatement();
        }
    }

    private Statement moveStatement() {
        Direction direction = direction();
        consume(TokenType.SEMICOLON, "Expect ';' after move command.");
        return new MoveCommand(direction);
    }

    private Statement doneStatement() {
        consume(TokenType.SEMICOLON, "Expect ';' after done command.");
        return new DoneCommand();
    }

    private Statement blockStatement() {
        List<Statement> statements = new ArrayList<>();
        while (!check(TokenType.RIGHT_BRACE) && !isAtEnd()) {
            statements.add(statement());
        }
        consume(TokenType.RIGHT_BRACE, "Expect '}' after block statement.");
        return new BlockStatement(statements);
    }

    private IfStatement ifStatement() {
        consume(TokenType.LEFT_PAREN, "Expect '(' after 'if'.");
        Expression condition = expression();
        consume(TokenType.RIGHT_PAREN, "Expect ')' after if condition.");
        Statement thenBranch = statement();
        if (match(TokenType.ELSE)) {
            Statement elseBranch = statement();
            return new IfStatement(condition, thenBranch, elseBranch);
        }
        return new IfStatement(condition, thenBranch, null);
    }

    private Statement whileStatement() {
        consume(TokenType.LEFT_PAREN, "Expect '(' after 'while'.");
        Expression condition = expression();
        consume(TokenType.RIGHT_PAREN, "Expect ')' after while condition.");
        Statement body = statement();
        return new WhileStatement(condition, body);
    }

    private Statement commandStatement() {
        if (match(TokenType.IDENTIFIER)) {
            Token identifier = previous();
            if (match(TokenType.EQUAL)) {
                Expression value = expression();
                consume(TokenType.SEMICOLON, "Expect ';' after variable assignment.");
                return new AssignmentStatement(identifier, value);
            } else {
                return actionCommand(identifier);
            }
        } else {
            throw error(peek(), "Expect statement.");
        }
    }

    private Statement actionCommand(Token identifier) {
        if (identifier.getType() == TokenType.RELOCATE) {
            consume(TokenType.SEMICOLON, "Expect ';' after relocate command.");
            return new RelocateCommand();
        } else if (identifier.getType() == TokenType.MOVE) {
            Direction direction = direction();
            consume(TokenType.SEMICOLON, "Expect ';' after move command.");
            return new MoveCommand(direction);
        } else if (identifier.getType() == TokenType.ATTACK) {
            Direction direction = direction();
            Expression strength = expression();
            consume(TokenType.SEMICOLON, "Expect ';' after attack command.");
            return new AttackCommand(direction, strength);
        } else if (identifier.getType() == TokenType.COLLECT) {
            Expression amount = expression();
            consume(TokenType.SEMICOLON, "Expect ';'after collect command.");
            return new CollectCommand(amount);
        } else if (identifier.getType() == TokenType.INVEST) {
            Expression amount = expression();
            consume(TokenType.SEMICOLON, "Expect ';'after invest command.");
            return new InvestCommand(amount);
        } else {
            throw error(peek(), "Expect command.");
        }
    }

    private Direction direction() {
        if (match(TokenType.UP)) {
            if (match(TokenType.LEFT)) {
                return Direction.UP_LEFT;
            } else if (match(TokenType.RIGHT)) {
                return Direction.UP_RIGHT;
            } else {
                return Direction.UP;
            }
        } else if (match(TokenType.DOWN)) {
            if (match(TokenType.LEFT)) {
                return Direction.DOWN_LEFT;
            } else if (match(TokenType.RIGHT)) {
                return Direction.DOWN_RIGHT;
            } else {
                return Direction.DOWN;
            }
        } else {
            throw error(peek(), "Expect direction after move command.");
        }
    }

    private Expression expression() {
        return additiveExpression();
    }

    private Expression additiveExpression() {
        Expression expr = term();

        while (match(TokenType.PLUS, TokenType.MINUS)) {
            Token operator = previous();
            Expression right = term();
            expr = new BinaryExpression(expr, String.valueOf(operator), right);
        }

        return expr;
    }

    private Expression term() {
        Expression expr = factor();

        while (match(TokenType.MULTIPLY, TokenType.DIVIDE, TokenType.MODULO)) {
            Token operator = previous();
            Expression right = factor();
            expr = new BinaryExpression(expr, String.valueOf(operator), right);
        }

        return expr;
    }

    private Expression factor() {
        Expression expr = power();

        while (match(TokenType.POWER)) {
            Token operator = previous();
            Expression right = factor();
            expr = new BinaryExpression(expr, String.valueOf(operator), right);
        }

        return expr;
    }

    private Expression power() {
        Expression expr = basicExpression();

        while (match(TokenType.CARAT)) {
            Token operator = previous();
            Expression right = basicExpression();
            expr = new Expression(List.of(), List.of(operator.getLexeme()));
        }

        return expr;
    }

    //POWER,PRIMARY!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }

        return false;
    }

    private Token consume(TokenType type, String message) {
        if (check(type)) {
            return advance();
        }

        throw error(peek(), message);
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) {
            return false;
        }

        return peek().getType() == type;
    }

    private Token advance() {
        if (!isAtEnd()) {
            current++;
        }

        return previous();
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    private boolean isAtEnd() {
        return peek().getType() == TokenType.EOF;
    }

    private ParserException error(Token token, String message) {
        return new ParserException(message);
    }

    private void synchronize() {
        advance();

        while (!isAtEnd()) {
            if (previous().getType() == TokenType.SEMICOLON) {
                return;
            }

            switch (peek().getType()) {
                case PLAN:
                case IF:
                case WHILE:
                case MOVE:
                case RELOCATE:
                case DONE:
                case SHOOT:
                case COLLECT:
                case INVEST:
                    return;
                default:
                    // Do nothing.
            }

            advance();
        }
    }
}

