import java.beans.Expression;

public class IfStatement implements Statement {
    private Expression expression;
    private Statement thenStatement;
    private Statement elseStatement;

    public IfStatement(Expression expression, Statement thenStatement, Statement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public void execute() {
        long value = expression.evaluate();
        if (value != 0) {
            thenStatement.execute();
        } else if (elseStatement != null) {
            elseStatement.execute();
        }
    }
}
