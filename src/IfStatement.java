class IfStatement extends Statement{
    private Expression expression;
    private Statement thenStatement;
    private Statement elseStatement;

    public IfStatement(Expression expression, Statement thenStatement, Statement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public void execute() {
        if (expression.evaluate()) {
            thenStatement.execute();
        } else {
            elseStatement.execute();
        }
    }
}