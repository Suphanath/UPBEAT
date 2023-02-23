import java.util.Map;

public interface Statement {
    long ev() throws SyntaxError;
    StringBuilder addCommand(StringBuilder s);

    void PrettyPrint(StringBuilder s);

    long ev(Map<String, Integer> bd);
}