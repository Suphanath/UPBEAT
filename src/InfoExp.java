public class InfoExp implements Statement {
    private String command;
    private Direction direction;
    private Player crew;

    public InfoExp(String command, Player crew) {
        this(command, null, crew);
    }

    public InfoExp(String command, Direction direction, Player crew) {
        this.command = command;
        this.direction = direction;
        this.crew = crew;
    }

    public String getCommand() {
        return command;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public long ev() throws SyntaxError {
        switch (command) {
            case "opponent":
                return crew.getOpponent();
            case "nearby":
                return crew.nearBy(direction);
            default:
                throw new SyntaxError("Invalid InfoExpr command: " + command);
        }
    }

    @Override
    public StringBuilder addCommand(StringBuilder sb) {
        sb.append("InfoExpr ");
        sb.append(command);
        if (direction != null) {
            sb.append(" ");
            sb.append(direction.toString().toLowerCase());
        }
        sb.append(" ");
        sb.append(crew.toString());
        return sb;
    }
}

