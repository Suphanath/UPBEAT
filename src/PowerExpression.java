public class PowerExpression extends Expression {
    private Expression base;
    private Expression exponent;

    public PowerExpression(Expression base, Expression exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    public boolean evaluate() {
        long baseValue = base.evaluate();
        long exponentValue = exponent.evaluate();

        if (exponentValue < 0) {
            throw new RuntimeException("Exponent must be nonnegative: " + exponentValue);
        }

        long result = 1;

        for (int i = 0; i < exponentValue; i++) {
            result *= baseValue;
        }

        return result;
    }
}