
import java.util.*;

public class Parsing{
    private Tokenizer tkz;
    public Parsing(Tokenizer tkz){
        this.tkz = tkz;
    }
    private Plan plans;
    Player crew;
    private static final Set<String> words = new HashSet<>(Arrays.asList(
            "collect", "done", "down", "downleft", "downright", "else", "if", "invest", "move",
            "nearby", "opponent", "relocate", "shoot", "then", "up", "upleft", "upright", "while"));


    public String parse(String txt,Player crew) throws SyntaxError {
        this.tkz = new Tokenizer(txt);
        plans = ParsePlan();
        this.crew = crew;
        return plans.ev();
    }

    private Plan ParsePlan() throws SyntaxError{
        Plan plan = new Plan();
        while (tkz.hasNextToken()){
            plan.addStatement(ParseStatement());
        }
        return plan;
    }

    private Statement ParseStatement() throws SyntaxError{
        if (tkz.peek().equals("if")) {
            return ParseIfStatement();
        } else if (tkz.peek().equals("while")) {
            return ParseWhileStatement();
        } else if (tkz.peek().equals("{")) {
            return ParseBlockStatement();
        } else {
            return ParseCommand();
        }
    }

    private Statement ParseCommand(){
        if (tkz.peek("done") || tkz.peek("relocate")
                || tkz.peek("move") || tkz.peek("invest")
                || tkz.peek("collect") || tkz.peek("shoot")) {
            return ParseActionCommand();
        } else {
            return ParseAssignmentStatement();
        }
    }

    private Statement ParseAssignmentStatement() {
        Identifier identifier = ParseIdentifier();
        tkz.consume("=");
        Statement expression = ParseExpression();
        return new AssignStatement(identifier, "=", expression);
    }

    private Identifier ParseIdentifier() {
        if (words.contains(tkz.peek())) {
            tkz.consume();
            throw new SyntaxError("Error");
        }
        if (!tkz.isNumber("" + tkz.peek().charAt(0))) {
            if (tkz.peek().substring(1).chars().allMatch(Character::isLetterOrDigit)) {
                return new Identifier(tkz.consume());
            }
        }
        throw new SyntaxError("Error");
    }

    private Statement ParseActionCommand() {
        if (tkz.peek().equals("move")) {
            return ParseMoveCommand();
        } else if (tkz.peek().equals("shoot")) {
            return ParseAttackCommand();
        } else if (tkz.peek().equals("invest") || tkz.peek().equals("collect")) {
            return ParseRegionCommand();
        } else if (tkz.peek().equals("done")) {
            return ParseDoneCommand();
        } else if (tkz.peek().equals("relocate")) {
            return ParseRelocateCommand();
        } else {
            throw new SyntaxError("SyntaxError");
        }
    }

    private Statement ParseRelocateCommand() {
        if (tkz.peek("relocate")) {
            tkz.consume();
            return new ActionCommand("relocate");
        }else {
            throw new SyntaxError("SyntaxError");
        }
    }

    private Statement ParseDoneCommand() {
        if (tkz.peek("done")) {
            tkz.consume();
            return new ActionCommand("done");
        }else {
            throw new SyntaxError("SyntaxError");
        }
    }

    private Statement ParseRegionCommand() {
        if (tkz.peek("invest")) {
            tkz.consume();
            return new ActionCommand("invest", ParseExpression());
        } else if (tkz.peek("collect")) {
            tkz .consume();
            return new ActionCommand("collect", ParseExpression());
        }else {
            throw new SyntaxError("SyntaxError");
        }
    }

    private Statement ParseExpression() {
        Statement term = ParseTerm();
        while (tkz.peek("+") || tkz.peek("-")) {
            if (tkz.peek().equals("+")) {
                tkz.consume();
                term = new Binary(term, ParseTerm(), "+");
            } else if (tkz.peek().equals("-")) {
                tkz.consume();
                term = new Binary(term, ParseTerm(), "-");
            }else {
                throw new SyntaxError("SyntaxError");
            }
        }
        return term;
    }

    private Statement ParseTerm() {
        Statement factor = ParseFactor();
        while (tkz.peek("*") || tkz.peek("/") || tkz.peek("%")) {
            if (tkz.peek().equals("*")) {
                tkz.consume();
                factor = new Binary(factor, ParseFactor(), "*");
            } else if (tkz.peek().equals("/")) {
                tkz.consume();
                factor = new Binary(factor, ParseFactor(), "/");
            } else if (tkz.peek().equals("%")) {
                tkz.consume();
                factor = new Binary(factor, ParseFactor(), "%");
            } else {
                throw new SyntaxError("Error");
            }
        }
        return factor;
    }

    private Statement ParseFactor() {
        Statement power = ParsePower();
        if (tkz.peek("^")) {
            tkz.consume("^");
            power = new Binary(power, ParseFactor(), "^");
        }
        return power;
    }

    private Statement ParsePower() {
        if (Tokenizer.isNumber(tkz.peek())) {
            return new IntLit(Integer.parseInt(tkz.consume()));
        } else if (tkz.peek("(")) {
            tkz.consume("(");
            Statement exp = ParseExpression();
            tkz.consume(")");
            return exp;
        } else if (tkz.peek("opponent") || tkz.peek("nearby")) {
            return ParseInfoExpression();
        } else {
            return ParseIdentifier();
        }
    }

    private Statement ParseInfoExpression() {
        if (tkz.peek().equals("opponent")) {
            tkz.consume();
            return new InfoExp("opponent", crew);
        } else if (tkz.peek().equals("nearby")) {
            tkz.consume();
            Direction direction = ParseDirection();
            return new InfoExp("nearby", direction, crew);
        } else {
            throw new SyntaxError("SyntaxError");
        }

    }

    private Statement ParseAttackCommand() {
        if (tkz.peek("shoot")) {
            tkz.consume();
            return new ActionCommand("shoot", ParseDirection());
        }else {
            throw new SyntaxError("SyntaxError");
        }
    }

    private Direction ParseDirection() {
        Direction direction = Direction.getDirection(tkz.peek());
        tkz.consume();
        if (direction != null) {
            return direction;
        } else {
            throw new SyntaxError("Error");
        }
    }

    private Statement ParseMoveCommand() {
        if (tkz.peek("move")) {
            tkz.consume();
            return new ActionCommand("move", ParseDirection());
        }else {
            throw new SyntaxError("SyntaxError");
        }
    }

    private Statement ParseWhileStatement() {
        tkz.consume("while");
        Statement Expression = ParseExpression();
        Statement trueStatement = ParseStatement();
        return new WhileStatement(Expression, trueStatement);
    }

    private Statement ParseIfStatement() {
        tkz.consume("if");
        tkz.consume("(");
        Statement Expression = ParseExpression();
        tkz.consume(")");
        tkz.consume("then");
        Statement trueStatement = ParseStatement();
        tkz.consume("else");
        Statement falseStatement = ParseStatement();
        return new IfStatement(Expression, trueStatement, falseStatement);
    }

    private BlockStatement ParseBlockStatement() {
        tkz.consume("{");
        LinkedList<Statement> state = new LinkedList<>();
        while (!tkz.peek("}")) {
            state.add(ParseStatement());
        }
        tkz.consume("}");
        return new BlockStatement(state);
    }
}