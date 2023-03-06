import java.util.Map;
import java.util.Random;

public class Identifier implements Statement{
    protected String val;
    protected Map<String, Long> var;

    public Identifier(String val) {
        this.val = val;
    }

    @Override
    public long ev() throws SyntaxError {
        if ("random".equals(val)) {
            return new Random().nextLong(1000);
        } else if (var.containsKey(val)) {
            return var.get(val);
        } else {
            throw new SyntaxError("Unknown variable: " + val);
        }
    }

    @Override
    public StringBuilder addCommand(StringBuilder sb) {
        return sb.append("Identifier called ").append(val).append(System.lineSeparator());
    }

}
