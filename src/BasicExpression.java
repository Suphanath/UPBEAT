public class BasicExpression {
    private Expression expression;

    public BasicExpression(Expression expression) {
        this.expression = expression;
    }

    public long evaluate(Environment env) {
        return expression.evaluate(env);
    }
}
