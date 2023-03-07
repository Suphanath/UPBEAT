
public class AssignStatement extends Command {
    private Identifier identifier;
    private Statement expression;
    private String op;

    public AssignStatement(Identifier identifier, String op, Statement expression) {
        this.identifier = identifier;
        this.expression = expression;
        this.op = op;
    }

    @Override
    public long ev() throws SyntaxError {
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        return null;
    }
}
