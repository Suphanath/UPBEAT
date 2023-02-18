class AssignmentStatement extends Statement {
    private String identifier;
    private Expression expression;

    public AssignmentStatement(Token identifier, Expression expression) {
        this.identifier = String.valueOf(identifier);
        this.expression = expression;
    }

    public void execute() {
        boolean value = expression.evaluate();
        Environment.setVariable(identifier, value);
    }
}