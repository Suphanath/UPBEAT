import java.util.Map;

public class Binary implements Statement {
    private Statement left;
    private Statement right;
    private String operator;

    public Binary(Statement left, Statement right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public String operator() {
        return operator;
    }

    @Override
    public long ev() throws ArithmeticException{
        long le = left.ev();
        long re = right.ev();
        long result;
        String operator = operator();
        if (operator.equals("+")) {
            result = le + re;
        } else if (operator.equals("-")) {
            result = le - re;
        } else if (operator.equals("*")) {
            result = le * re;
        } else if (operator.equals("/")) {
            result = le / re;
        } else if (operator.equals("%")) {
            result = le % re;
        } else if (operator.equals("^")) {
            result = (int) Math.pow(le, re);
        } else {
            throw new SyntaxError("Error");
        }
        return result;
    }

    @Override
    public StringBuilder addCommand(StringBuilder sb) {
        sb.append("Expression ");
        return sb;
    }
}