public class WhileStatement extends Statement {
    private Expression condition;
    private Statement body;

    public WhileStatement(Expression condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }

    public void execute() {
        while (condition.evaluate()) {
            body.execute();
        }
    }
}
