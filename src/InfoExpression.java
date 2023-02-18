public class InfoExpression extends Expression {
    private String info;

    public InfoExpression(String info) {
        this.info = info;
    }

    public boolean evaluate() {
        // Return a placeholder value for info expressions
        return 0;
    }
}