public class BinaryExpression extends Expression {
    private Expression left;
    private Expression right;
    private String operator;

    public BinaryExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public BinaryExpression(Expression left, String operator, Expression right) {
        super();
    }

    public boolean evaluate() {
        long leftValue = left.evaluate();
        long rightValue = right.evaluate();

        if (operator.equals("+")) {
            return leftValue + rightValue;
        } else if (operator.equals("-")) {
            return leftValue - rightValue;
        } else if (operator.equals("*")) {
            return leftValue * rightValue;
        } else if (operator.equals("/")) {
            return leftValue / rightValue;
        } else if (operator.equals("%")) {
            return leftValue % rightValue;
        } else {
            throw new RuntimeException("Unknown operator: " + operator);
        }
    }
}