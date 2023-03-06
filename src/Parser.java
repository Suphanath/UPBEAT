public interface Parser {
    String parse() throws SyntaxError;

    String Parse(String stream) throws SyntaxError;
}
