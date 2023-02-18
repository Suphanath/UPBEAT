public class InvestCommand extends Command {
    private Expression amount;

    public InvestCommand(Expression amount) {
        super();
        this.amount = amount;
    }

    @Override
    public void execute(Environment env) {
        long value = amount.evaluate(env);
        env.invest(value);
    }
}
