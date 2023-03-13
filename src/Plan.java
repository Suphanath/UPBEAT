import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Plan {
    private List<Statement> statements;

    // Constructor that takes a List<Statement>
    public Plan(List<Statement> statements) {
        this.statements = statements;
    }

    private int currentIndex = 0;


//    public void addStatement(Statement statement) {
//        statements.add(statement);
//    }


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
        StringBuilder commands = new StringBuilder();
        reset();
        Statement statement;
        while ((statement = getNextStatement()) != null) {
            statement.ev();
            commands = statement.addCommand(commands);
        }
        return commands.toString();
    }
}

