public class Power {
    private BasicExpression base;
    private Factor exponent;

    public Power(BasicExpression base, Factor exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    public long evaluate(Environment env) {
        long baseValue = base.evaluate(env);
        long exponentValue = exponent.evaluate();
        return (long) Math.pow(baseValue, exponentValue);
    }
}
