import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Territory {
    private final List<Region> regions;;
    private final int rows;
    private final int cols;
    public int row() {
        return rows;
    }

    public int col() {
        return cols;
    }

    public Territory() {
        this.rows = Configs.conf().m;
        this.cols = Configs.conf().n;
        this.regions = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                regions.add(new Region(i, j));
            }
        }
    }

    public Region region(long[] position) {
        int row = (int) position[0];
        int col = (int) position[1];
        int reg = row * cols + col;
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("Invalid position: " + Arrays.toString(position));
        }
        return regions.get(reg);
    }

    public void regionInfo() {
        for (int i = 0; i < rows; i++) {
            System.out.println("Row " + i);
            for (int j = 0; j < cols; j++) {
                int reg = i * cols + j;
                regions.get(reg).regionInfo();
            }
            System.out.println();
        }
    }
}
