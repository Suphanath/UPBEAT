import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class BlockStatement implements Statement {
    private List<Statement> statements;

    public BlockStatement(LinkedList<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public void execute() {
        for (Statement stmt : statements) {
            stmt.execute();
        }
    }
}
