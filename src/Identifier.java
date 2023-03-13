import java.util.Map;
import java.util.Random;

public class Identifier implements Statement{
    private String val;
    public Variables var;

    public Identifier(String val) {
        this.val = val;
    }

    public Identifier(String val,Variables var) {
        this.val = val;
        this.var = var;
    }

    @Override
    public long ev() throws SyntaxError {
        String v = val;
        if ("random".equals(val)) {
            return new Random().nextLong(1000);
        } else if (var.variables.containsKey(v)) {
            return var.variables.get(v);
        } else {
            throw new SyntaxError("Unknown variable: " + val);
        }
    }

    @Override
    public StringBuilder addCommand(StringBuilder sb) {
        return sb.append("Identifier called ").append(val).append(System.lineSeparator());
    }
}
