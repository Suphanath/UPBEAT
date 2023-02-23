import java.util.Map;

public interface Expr {
    int ev(Map<String, Integer> bd);
    void PrettyPrint(StringBuilder sb);
}