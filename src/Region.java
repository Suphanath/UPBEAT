public class Region {
    private int x;
    private int y;
    private int deposit;
    private Player owner;
    private Region[] neighbors;

    public Region(int x, int y, int deposit) {
        this.x = x;
        this.y = y;
        this.deposit = deposit;
        this.owner = null;
        this.neighbors = new Region[6];
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getDeposit() {
        return this.deposit;
    }

    public void setDeposit(int deposit) {
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

    public Region getNeighbor(int direction) {
        return this.neighbors[direction];
    }

    public void setNeighbor(int direction, Region region) {
        this.neighbors[direction] = region;
    }

    public void addDeposit(int amount) {
        this.deposit += amount;
    }

    public void deductDeposit(int amount) {
        this.deposit -= amount;
    }
}
