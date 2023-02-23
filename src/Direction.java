import java.util.HashMap;

public class Direction {


    private static final HashMap<Object, Object> DIRECTION_MAP = new HashMap<>();

    public static Direction getDirection(String direction) {
        return (Direction) DIRECTION_MAP.getOrDefault(direction, null);
    }
}
