import java.beans.Expression;
import java.util.Map;

public class AssignStatement extends Command {
    private String identifier;
    private Expression expression;

    public AssignStatement(String identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public long ev() throws SyntaxError {
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        return null;
    }

    @Override
    public void PrettyPrint(StringBuilder s) {

    }

    @Override
    public long ev(Map<String, Integer> bd) {
        return 0;
    }

    @Override
    public void execute() {
        long value = expression.evaluate();
        // store the value in the identifier
    }
}
