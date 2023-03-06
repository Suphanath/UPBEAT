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

}
