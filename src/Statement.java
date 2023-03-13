public interface Statement {
    long ev() throws SyntaxError;
    StringBuilder addCommand(StringBuilder sb);
}