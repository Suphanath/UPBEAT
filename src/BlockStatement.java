import java.util.List;

public class BlockStatement extends Statement {
    private List<Statement> statements;

    public BlockStatement(List<Statement> statements) {
        this.statements = statements;
    }

    public void execute() {
        for (Statement statement : statements) {
            statement.execute();
        }
    }
}
