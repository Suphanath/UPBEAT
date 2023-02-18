import java.util.HashMap;
import java.util.Map;

public class IdentifierExpression extends Expression {
    private String name;
    private Map<String, Long> variables;

    public IdentifierExpression(String name) {
        this.name = name;
        this.variables = new HashMap<>();
    }

    public void setValue(long value) {
        variables.put(name, value);
    }

    public boolean evaluate() {
        if (!variables.containsKey(name)) {
            throw new RuntimeException("Undefined variable: " + name);
        }

        return variables.get(name);
    }
}