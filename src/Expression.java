import java.util.List;

public class Expression {
    private List<Term> terms;
    private List<String> operators;

    public Expression(List<Term> terms, List<String> operators) {
        this.terms = terms;
        this.operators = operators;
    }

    public long evaluate(Environment env) {
        long result = 0;
        boolean firstTerm = true;

        for (Term term : terms) {
            long value = term.evaluate(env);
            if (firstTerm) {
                result = value;
                firstTerm = false;
            } else {
                result += value * (term.getOperator().equals("+") ? 1 : -1);
            }
        }

        return result;
    }
}
