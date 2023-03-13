
public class AssignStatement extends Command {
    private Identifier identifier;
    private Statement expression;
    private String operator;

    public AssignStatement(Identifier identifier, String operator, Statement expression) {
        this.identifier = identifier;
        this.expression = expression;
        this.operator = operator;
    }

    public Identifier Identifier() {
        return identifier;
    }

    public String Operator() {
        return operator;
    }

    public Statement Expression() {
        return expression;
    }

    @Override
    public long ev() throws SyntaxError {
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder sb) {
        sb.append("AssignStatement ");
        return sb;
    }
}
