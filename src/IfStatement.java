
public class IfStatement implements Statement{
    private Statement trueState, falseState, expression;

    public IfStatement(Statement expression, Statement trueState, Statement falseState) {
        this.expression = expression;
        this.trueState = trueState;
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
    public StringBuilder addCommand(StringBuilder sb) {
        sb.append("Ifstatement ");
        return sb;}
}