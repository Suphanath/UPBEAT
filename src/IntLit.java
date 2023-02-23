import java.util.Map;
public class IntLit implements Statement{
    private final long val;
    public IntLit(long val){
        this.val = val;
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
        s.append(val);
    }
    @Override
    public long ev(Map<String, Integer> bd) {
        return val;
    }

    @Override
    public void execute() throws ExecutionError {

    }
}
