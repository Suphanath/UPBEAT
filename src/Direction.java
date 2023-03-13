import java.util.HashMap;
import java.util.Map;

public enum Direction {
    UP("UP"),
    DOWN("DOWN"),
    UPLEFT("UPLEFT"),
    UPRIGHT("UPRIGHT"),
    DOWNLEFT("DOWNLEFT"),
    DOWNRIGHT("DOWNRIGHT");

    private static final Map<String, Direction> DIRECTION_MAP = new HashMap<>();
    private final String name;

    static {
        for (Direction direction : Direction.values()) {
            DIRECTION_MAP.put(direction.name, direction);
        }
    }

    Direction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Direction getDirection(String direction) {
        return DIRECTION_MAP.getOrDefault(direction, null);
    }
}

