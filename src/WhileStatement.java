

public class WhileStatement implements Statement {
    private  Statement expression;
    private  Statement trueStatement;

    public WhileStatement(Statement expression, Statement trueStatement) {
        this.expression = expression;
        this.trueStatement = trueStatement;
    }

    public Statement Expression() {
        return expression;
    }

    public Statement trueState() {
        return trueStatement;
    }


    @Override
    public long ev() throws SyntaxError {
        for (int counter = 0; counter < 10000 && Expression().ev() > 0; counter++){
            trueStatement.ev();
        }
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder sb) {
        sb.append("Whilestatement ");
        return sb;
    }
}