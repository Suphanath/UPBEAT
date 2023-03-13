import java.util.Map;
public class IntLit implements Statement{
    private long val;
    public IntLit(long val){
        this.val = val;
    }

    public long eval(){
        return val;
    }

    @Override
    public long ev() throws SyntaxError {
        return val;
    }

    @Override
    public StringBuilder addCommand(StringBuilder sb) {
        sb.append("IntLit ");
        return sb;
    }
}
