public class UnaryExpression extends Expression {
    private Expression expression;
    private String operator;

    public UnaryExpression(Expression expression, String operator) {
        this.expression = expression;
        this.operator = operator;
    }

    public boolean evaluate() {
        long value = expression.evaluate();

        if (operator.equals("-")) {
            return -value;
        } else {
            throw new RuntimeException("Unknown operator: " + operator);
        }
    }
}
