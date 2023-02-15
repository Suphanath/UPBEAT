import java.util.*;

public class Parsing {
    private List<String> tokens;
    private int currentToken;

    public void parse(List<String> tokens) throws Exception {
        this.tokens = tokens;
        this.currentToken = 0;
        plan();
    }

    private void plan() throws Exception {
        while (currentToken < tokens.size()) {
            statement();
        }
    }

    private void statement() throws Exception {
        String token = getNextToken();
        switch (token) {
            case "done":
            case "relocate":
                break;
            case "move":
                moveCommand();
                break;
            case "invest":
            case "collect":
                regionCommand();
                break;
            case "shoot":
                attackCommand();
                break;
            case "{":
                blockStatement();
                break;
            case "if":
                ifStatement();
                break;
            case "while":
                whileStatement();
                break;
            default:
                if (isIdentifier(token)) {
                    assignmentStatement();
                } else {
                    throw new Exception("Unexpected token: " + token);
                }
        }
    }

    private void assignmentStatement() throws Exception {
        match("=");
        expression();
    }

    private void actionCommand() throws Exception {
        // done and relocate do not have arguments
        // move, invest, and collect have one argument
        // shoot has two arguments
        switch (getNextToken()) {
            case "move":
                direction();
                break;
            case "invest":
            case "collect":
                expression();
                break;
            case "shoot":
                direction();
                expression();
                break;
            default:
                throw new Exception("Invalid action command");
        }
    }

    private void moveCommand() throws Exception {
        direction();
    }

    private void regionCommand() throws Exception {
        expression();
    }

    private void attackCommand() throws Exception {
        direction();
        expression();
    }

    private void direction() throws Exception {
        switch (getNextToken()) {
            case "up":
            case "down":
            case "upleft":
            case "upright":
            case "downleft":
            case "downright":
                break;
            default:
                throw new Exception("Invalid direction");
        }
    }

    private void blockStatement() throws Exception {
        while (!peekToken().equals("}")) {
            statement();
        }
        match("}");
    }

    private void ifStatement() throws Exception {
        match("(");
        expression();
        match(")");
        statement();
        if (peekToken().equals("else")) {
            match("else");
            statement();
        }
    }

    private void whileStatement() throws Exception {
        match("(");
        expression();
        match(")");
        statement();
    }

    private void expression() throws Exception {
        term();
        while (isAddOp(peekToken())) {
            getNextToken();
            term();
        }
    }

    private void term() throws Exception {
        factor();
        while (isMulOp(peekToken())) {
            getNextToken();
            factor();
        }
    }

    private void factor() throws Exception {
        power();
        while (peekToken().equals("^")) {
            getNextToken();
            factor();
        }
    }

    private void power() throws Exception {
        String token = getNextToken();
        if (isNumber(token) || isIdentifier(token)) {
            // Number or identifier
        } else if (token.equals("(")) {
            expression();
            match(")");
        } else if (token.equals("opponent") || token.equals("nearby")) {
            direction();
        } else {
            throw new Exception("Invalid expression");
        }
    }

    private boolean isNumber(String token) {
        return token.matches("\\d+");
    }

    private boolean isIdentifier(String token) {
        return token.matches("[a-zA-Z][a-zA-Z0-9]*");
    }

    private boolean isAddOp(String token) {
        return token.equals("+") || token.equals("-");
    }

    private boolean isMulOp(String token) {
        return token.equals("*") || token.equals("/") || token.equals("%");
    }

    private String getNextToken() throws Exception {
        if (currentToken >= tokens.size()) {
            throw new Exception("Unexpected end of input");
        }
        return tokens.get(currentToken++);
    }

    private String peekToken() throws Exception {
        if (currentToken >= tokens.size()) {
            throw new Exception("Unexpected end of input");
        }
        return tokens.get(currentToken);
    }

    private void match(String expected) throws Exception {
        if (peekToken().equals(expected)) {
            getNextToken();
        } else {
            throw new Exception("Expected " + expected);
        }
    }
}

