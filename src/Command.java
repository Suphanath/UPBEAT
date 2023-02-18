public abstract class Command extends Statement {
    private Statement statement;

    public Command() {
        this.statement = statement;
    }

    public void execute() {
        statement.execute();
    }

    public abstract void execute(Environment env);
}