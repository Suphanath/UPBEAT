import Unit.Unit;

public class ActionCommand implements Statement {

    private String action;
    private Direction direction;
    private Statement expression;

    private Player crew;

    public ActionCommand(String action, Direction direction) {
        this.action = action;
        this.direction = direction;
    }

    public ActionCommand(String action, Statement expression) {
        this.action = action;
        this.expression = expression;
    }

    public ActionCommand(String action) {
        this.action = action;
    }

    public void execute() throws SyntaxError {
        if (action.equals("move")) {
            crew.move(direction);
        } else if (action.equals("shoot")) {
            crew.shoot(direction);
        } else if (action.equals("invest")) {
            crew.invest();
        } else if (action.equals("collect")) {
            crew.collect();
        } else if (action.equals("done")) {
            crew.done();
        } else if (action.equals("relocate")) {
            crew.relocate();
        } else {
            throw new SyntaxError("Unknown action: " + action);
        }
    }


    @Override
    public long ev() throws SyntaxError {
        execute();
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder sb) {
        sb.append("ActionStatement ");
        return sb;
    }
}

