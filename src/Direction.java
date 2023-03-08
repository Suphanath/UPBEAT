import java.util.HashMap;

public class Direction {
    public static final Direction UP = new Direction("UP");
    public static final Direction DOWN = new Direction("DOWN");
    public static final Direction UPLEFT = new Direction("UPLEFT");
    public static final Direction UPRIGHT = new Direction("UPRIGHT");
    public static final Direction DOWNLEFT = new Direction("DOWNLEFT");
    public static final Direction DOWNRIGHT = new Direction("DOWNRIGHT");

    private String name;

    private Direction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public static int ordinal() {
        return Direction.ordinal();
    }
    public static Direction[] values() {
        return Direction.values();
    }

    private static final HashMap<Object, Object> DIRECTION_MAP = new HashMap<>();

    public static Direction getDirection(String direction) {
        return (Direction) DIRECTION_MAP.getOrDefault(direction, null);
    }
}
