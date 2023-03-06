import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface Statement {
    long ev() throws SyntaxError;
    StringBuilder addCommand(StringBuilder sb);
}