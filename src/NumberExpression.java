public class NumberExpression extends Expression {
    private long value;

    public NumberExpression(long value) {
        this.value = value;
    }

    public boolean evaluate() {
        return value;
    }
}