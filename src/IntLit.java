import java.util.Map;
public class IntLit implements Expr{
    private final int val;
    public IntLit(int val){
        this.val = val;
    }
    @Override
    public void PrettyPrint(StringBuilder s) {
        s.append(val);
    }
    @Override
    public int ev(Map<String, Integer> bd) {
        return val;
    }
}
