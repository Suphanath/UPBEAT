import java.beans.Expression;

public class WhileStatement extends Statement {
    private Expression expression;
    private Statement statement;

    public WhileStatement(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public void execute() {
        while (expression.evaluate() != 0) {
            statement.execute();
        }
    }
}