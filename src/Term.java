import java.util.List;

class Term {
    private final List<Factor> factors;
    private final List<String> operators;
    private Factor left;
    private Token operator;
    private Term right;

    public Term(List<Factor> factors, List<String> operators, Factor left) {
        this.factors = factors;
        this.operators = operators;
        this.left = left;
    }

    public void setOperator(Token operator) {
        this.operator = operator;
    }

    public void setRight(Term right) {
        this.right = right;
    }

    public long evaluate(Environment env) {
        long leftValue = left.evaluate();
        if (right == null) {
            return leftValue;
        }

        long rightValue = right.evaluate(env);

        switch (operator.getType()) {
            case MULTIPLY:
                return leftValue * rightValue;
            case DIVIDE:
                return leftValue / rightValue;
            case MODULO:
                return leftValue % rightValue;
            default:
                throw new ParserException("Invalid operator: " + operator);
        }
    }

    public String getOperator() {
        return operators.get(index);
    }
}