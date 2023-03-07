import java.util.LinkedList;

public class BlockStatement implements Statement{
    private LinkedList<Statement> statement;

    public BlockStatement(LinkedList<Statement> list) {
        this.statement = list;
    }

    public LinkedList<Statement> getList() {
        return statement;
    }

    @Override
    public long ev() throws SyntaxError {
        for (Statement st : statement) {
            st.ev();
        }
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder sb) {
        sb.append("Blockstatement ");
        return sb;
    }
}
