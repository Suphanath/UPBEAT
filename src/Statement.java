import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface Statement {
    long ev() throws SyntaxError;
    StringBuilder addCommand(StringBuilder s);

    void PrettyPrint(StringBuilder s);

    long ev(Map<String, Integer> bd);
    void execute() throws ExecutionError;
}