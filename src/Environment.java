import java.util.HashMap;

public class Environment {
    private HashMap<String, Long> variables;

    public Environment() {
        variables = new HashMap<String, Long>();
    }

    public static void setVariable(String identifier, boolean value) {
    }

    public void assign(String varName, long value) {
        variables.put(varName, value);
    }

    public long get(String varName) {
        Long value = variables.get(varName);
        if (value == null) {
            throw new ParserException("Variable not defined: " + varName);
        }
        return value;
    }

    public void collect(boolean regionValue) {
        // implementation omitted
    }

    public void invest(long value) {
    }
}
