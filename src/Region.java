import java.lang.Math;

public class Region {
    private int x;
    private int y;
    private double deposit;
    private Player owner;
    private Region[] neighbors;

    public Region(int x, int y, double deposit) {
        this.x = x;
        this.y = y;
        this.deposit = deposit;
        this.owner = null;
        this.neighbors = new Region[6];
    }

    // getter and setter methods
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double getDeposit() {
        return this.deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean belongsTo(Player player) {
        return this.owner == player;
    }

    public int getMaxDeposit() {
        return (this.x + this.y) * 10;
    }

    public Region[] getNeighbors() {
        return this.neighbors;
    }

    public Region getNeighbor(Direction direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Direction cannot be null.");
        }
        if (this.neighbors == null) {
            return null;
        }
        Region neighbor = null;
        try {
            neighbor = this.neighbors[direction.ordinal()];
        } catch (ArrayIndexOutOfBoundsException e) {
            // handle invalid direction
        }
        return neighbor;
    }

    // utility methods
    public void addDeposit(double amount) {
        this.deposit += amount;
    }

    public void deductDeposit(double amount) {
        this.deposit -= amount;
    }

    // method for calculating interest for the current turn
    public double calculateInterest(double baseRate, int turnCount) {
        double rate = baseRate * Math.log10(this.deposit) * Math.log(turnCount);
        double interest = this.deposit * rate / 100;
        this.deposit += interest;
        return this.deposit;
    }
}
