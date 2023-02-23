import java.util.Map;

public class Binary implements Expr {
    private Expr left;
    private Expr right;
    private String operator;

    public Binary(Expr left, Expr right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Binary(Expr left, String operator, Expr right){
        super();
    }

    public int ev(Map<String, Integer> bd) throws ArithmeticException{
        int le = left.ev(bd);
        int re = right.ev(bd);
        switch (operator) {
            case "+" -> { return le + re; }
            case "-" -> { return le - re; }
            case "*" -> { return le * re; }
            case "/" -> {
                if (re  == 0)
                    throw new ArithmeticException(le + " / " + re + " is error");
                return le / re;
            }
            case "%" -> {
                if (re == 0)
                    throw new ArithmeticException(le + " % " + re + " is error");
                return le % re;
            }
        }
        throw new ArithmeticException("error input");
    }

    @Override
    public void PrettyPrint(StringBuilder sb) {
        sb.append("(");
        left.PrettyPrint(sb);
        sb.append(operator);
        right.PrettyPrint(sb);
        sb.append(")");
    }
}