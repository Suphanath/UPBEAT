import java.util.NoSuchElementException;

public class Parsing {
    import java.util.NoSuchElementException;

    public class Parsing implements Parser {
        private final Tokenizer tkz;
        public Parsing(Tokenizer tkz){
            this.tkz = tkz;
        }

        @Override
        public Exp parse() throws SyntaxError {
            try{
                Exp rs = E();
                if(tkz.hasNextToken())
                    throw new SyntaxError("token " + tkz.peek() + " is not null");
                return rs;
            }catch (SyntaxError e){
                throw e;
            }
        }

        private Exp E() throws SyntaxError{
            try{
                Exp rs = T();
                while (tkz.hasNextToken() && (tkz.peek("+") || tkz.peek("-"))){
                    if(tkz.peek("+")){
                        tkz.consume();
                        rs = new Binary(rs, "+", T());
                    } else {
                        tkz.consume();
                        rs = new Binary(rs, "-", T());
                    }
                }
                return rs;
            } catch (IllegalArgumentException | NoSuchElementException e){
                throw new SyntaxError(e.getMessage());
            }
        }

        private Exp T() throws SyntaxError{
            try{
                Exp rs = F();
                while (tkz.hasNextToken() &&(tkz.peek("*") || tkz.peek("/") || tkz.peek("%"))){
                    if(tkz.peek("*")){
                        tkz.consume();
                        rs = new Binary(rs, "*", F());
                    } else if (tkz.peek("/")) {
                        tkz.consume();
                        rs = new Binary(rs, "/", F());
                    } else {
                        tkz.consume();
                        rs = new Binary(rs, "%", F());
                    }
                }
                return rs;
            }catch (IllegalArgumentException | NoSuchElementException e){
                throw new SyntaxError(e.getMessage());
            }
        }

        private Exp F() throws SyntaxError{
            try{
                if(Character.isDigit(tkz.peek().charAt(0))){
                    return  new IntLit(Integer.parseInt(tkz.consume()));
                }else if(Character.isAlphabetic(tkz.peek().charAt(0))){
                    return new Var(tkz.consume());
                }else {
                    Exp rs = null;
                    if(tkz.peek("(")) {
                        tkz.consume("(");
                        rs = E();
                        tkz.consume(")");
                    }
                    return rs;
                }
            }catch (NoSuchElementException | NumberFormatException | SyntaxError s){
                throw new SyntaxError(s.getMessage());
            }
        }
    }
}
