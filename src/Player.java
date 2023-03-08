public class Player {
    private boolean isDone;
    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;
    private static final int NORTHEAST = 4;
    private static final int SOUTHEAST = 5;
    private static final int[] DIRECTIONS = {NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST};
    private Region currentRegion;
    private Player opponent;
    private int budget;

    public int opponent() {
        int minDistance = Integer.MAX_VALUE;
        int opponentLocation = 0;
        for (int direction : DIRECTIONS) {
            Region neighbor = getNeighbor(direction);
            if (neighbor != null && neighbor.belongsTo(opponent)) {
                int distance = Math.abs(neighbor.getX() - this.currentRegion.getX())
                        + Math.abs(neighbor.getY() - this.currentRegion.getY());
                if (distance < minDistance) {
                    minDistance = distance;
                    opponentLocation = (distance * 10) + direction + 1;
                }
            }
        }
        return opponentLocation;
    }

    public int nearby(int direction) {
        Region neighbor = getNeighbor(direction);
        if (neighbor == null || !neighbor.belongsTo(opponent)) {
            return 0;
        }
        int distance = Math.abs(neighbor.getX() - this.currentRegion.getX())
                + Math.abs(neighbor.getY() - this.currentRegion.getY());
        int depositDigits = String.valueOf(neighbor.getDeposit()).length();
        return (distance * 100) + depositDigits;
    }

    public Region getNeighbor(int direction) {
        int x = this.currentRegion.getX();
        int y = this.currentRegion.getY();
        switch (direction) {
            case NORTH:
                y--;
                break;
            case EAST:
                x++;
                break;
            case SOUTH:
                y++;
                break;
            case WEST:
                x--;
                break;
            case NORTHEAST:
                x++;
                y--;
                break;
            case SOUTHEAST:
                x++;
                y++;
                break;
            default:
                return null;
        }
        String key = x + "," + y;
        if (!this.regions.containsKey(key)) {
            return null; // Neighbor is not in the map
        }
        return this.regions.get(key);
    }

    public void done() {
        this.isDone = true;
    }
    public void relocate(Region targetRegion) {
        if (!targetRegion.belongsTo(this) || this.getBudget() < calculateRelocationCost(targetRegion)) {
            return;
        }
        this.deductBudget(calculateRelocationCost(targetRegion));
        this.currentRegion = targetRegion;
        this.isDone = true;
    }
    private int calculateRelocationCost(Region targetRegion) {
        int x = Math.abs(this.currentRegion.getX() - targetRegion.getX()) + Math.abs(this.currentRegion.getY() - targetRegion.getY());
        return 5*x + 10;
    }
    public void move(Direction direction) {
        Region targetRegion = this.currentRegion.getNeighbor(direction);
        if (targetRegion == null || targetRegion.belongsTo(opponent)) {
            this.deductBudget(1);
            return;
        }
        this.currentRegion = targetRegion;
        this.deductBudget(1);
        this.isDone = true;
    }
    public void invest(int investmentAmount) {
        if (this.getBudget() < investmentAmount + 1) {
            this.deductBudget(1);
            return;
        }
        int totalCost = investmentAmount + 1;
        int depositToAdd = Math.min(investmentAmount, this.currentRegion.getMaxDeposit() - this.currentRegion.getDeposit());
        this.currentRegion.addDeposit(depositToAdd);
        this.deductBudget(totalCost);
        this.isDone = true;
    }
    public void collect(int collectionAmount) {
        if (this.getBudget() < 1 || collectionAmount > this.currentRegion.getDeposit()) {
            this.deductBudget(1);
            return;
        }
        this.addBudget(collectionAmount);
        this.currentRegion.deductDeposit(collectionAmount);
        if (this.currentRegion.getDeposit() == 0) {
            this.currentRegion.setOwner(null);
        }
        this.deductBudget(1);
        this.isDone = true;
    }
    public void shoot(Direction direction, int expenditure) {
        Region targetRegion = this.currentRegion.getNeighbor(direction);
        if (targetRegion == null) {
            this.deductBudget(1);
            return;
        }
        int totalCost = expenditure + 1;
        if (this.getBudget() < totalCost) {
            this.deductBudget(1);
            return;
        }
        this.deductBudget(totalCost);
        if (targetRegion.belongsTo(this)) {
            targetRegion.setDeposit(Math.max(0, targetRegion.getDeposit() - expenditure));
            if (targetRegion.getDeposit() < 1) {
                targetRegion.setOwner(null);
            }
        } else if (!targetRegion.belongsTo(opponent)) {
            // Unoccupied region, no effect
        } else {
            opponent.receiveAttack(targetRegion, expenditure);
        }
        this.isDone = true;
    }

    public int getBudget() {
        return this.budget;
    }

    public void deductBudget(int amount) {
        this.budget -= amount;
    }

    public void addBudget(int amount) {
        this.budget += amount;
    }

    public void receiveAttack(Region region, int expenditure) {
        if (region == null) {
            return;
        }
        int damage = Math.min(region.getDeposit(), expenditure);
        region.deductDeposit(damage);
        if (region.getDeposit() == 0) {
            region.setOwner(null);
        }
    }
}