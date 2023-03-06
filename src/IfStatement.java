
public class IfStatement implements Statement{
    private Statement expression;
    private Statement trueState;
    private Statement falseState;

    public IfStatement(Statement expression, Statement trueState, Statement falseState) {
        this.trueState = trueState;
        this.expression = expression;
        this.falseState = falseState;
    }

    public Statement Expression() {
        return expression;
    }

    public Statement trueState() {
        return trueState;
    }

    public Statement falseState() {
        return falseState;
    }

    @Override
    public long ev() throws SyntaxError {
        if (Expression().ev() > 0) {
            trueState.ev();
        } else {
            falseState.ev();
        }
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        s.append("Ifstatement ");
        return  s;}
}