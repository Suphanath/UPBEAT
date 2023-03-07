import java.util.ArrayList;
import java.util.List;

public class Plan {
    private final List<Statement> statements = new ArrayList<>();
    private int currentIndex = 0;

    public void addStatement(Statement statement) {
        statements.add(statement);
    }

    public Statement getNextStatement() {
        if (currentIndex >= statements.size()) {
            return null;
        }
        Statement statement = statements.get(currentIndex++);
        return statement;
    }

    public void reset() {
        currentIndex = 0;
    }

    public String ev() throws SyntaxError {
        StringBuilder commandList = new StringBuilder();
        reset();
        Statement statement;
        while ((statement = getNextStatement()) != null) {
            statement.ev();
            commandList = statement.addCommand(commandList);
        }
        return commandList.toString();
    }
}
