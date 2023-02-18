public class Factor {
    private Power power;
    private boolean negated;

    public Factor(Power power, boolean negated) {
        this.power = power;
        this.negated = negated;
    }

    public long evaluate() {
        long value = power.evaluate(env);
        return negated ? -value : value;
    }
}
