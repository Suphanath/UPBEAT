
public class ActionCommand implements Statement {
    private String action;
    private Direction direction;
    private Statement expression;
    private Player crew;

    public ActionCommand(String action, Direction direction, Player crew) {
        this.action = action;
        this.direction = direction;
        this.crew = crew;
    }

    public ActionCommand(String action, Statement expression, Player crew) {
        this.action = action;
        this.expression = expression;
        this.crew = crew;
    }

    public ActionCommand(String action, Direction direction, Statement expression, Player crew) {
        this.action = action;
        this.expression = expression;
        this.direction = direction;
        this.crew = crew;
    }

    public ActionCommand(String action,Player crew) {
        this.action = action;
        this.crew = crew;
    }

    public long ev() throws SyntaxError {
        if (action.equals("move")) {
            crew.move(direction);
        } else if (action.equals("shoot")) {
            crew.shoot(direction, expression.ev());
        } else if (action.equals("invest")) {
            crew.invest(expression.ev());
        } else if (action.equals("collect")) {
            crew.collect((int) expression.ev());
        } else if (action.equals("done")) {
            crew.done();
        } else if (action.equals("relocate")) {
            crew.relocate(null);
        } else {
            throw new SyntaxError("Unknown action: " + action);
        }
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder sb) {
        sb.append("ActionStatement " + direction);
        return sb;
    }
}

