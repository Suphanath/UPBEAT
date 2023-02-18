public class MoveCommand extends Statement {
    private Direction direction;

    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    public void execute() {
        // Implement the move command here
    }
}