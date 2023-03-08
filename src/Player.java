import java.util.ArrayList;
import java.util.List;

public class Player{
    private boolean isDone;
    private Region currentRegion;
    private Player opponent;
    private int budget;
    List<Region> allRegions = new ArrayList<>();

    public int opponent() {
        int minDistance = Integer.MAX_VALUE;
        int opponentLocation = 0;
        for (Direction direction : Direction.values()) {
            Region neighbor = getNeighbor(direction);
            if (neighbor != null && neighbor.belongsTo(opponent)) {
                int distance = Math.abs(neighbor.getX() - this.currentRegion.getX())
                        + Math.abs(neighbor.getY() - this.currentRegion.getY());
                if (distance < minDistance) {
                    minDistance = distance;
                    opponentLocation = (distance * 10) + direction.ordinal() + 1;
                }
            }
        }
        return opponentLocation;
    }

    public int nearby(Direction direction) {
        Region neighbor = getNeighbor(direction);
        if (neighbor == null || !neighbor.belongsTo(opponent)) {
            return 0;
        }
        int distance = Math.abs(neighbor.getX() - this.currentRegion.getX())
                + Math.abs(neighbor.getY() - this.currentRegion.getY());
        int depositDigits = String.valueOf(neighbor.getDeposit()).length();
        return (distance * 100) + depositDigits;
    }

    public Region getNeighbor(Direction direction) {
        int neighborX = this.currentRegion.getX();
        int neighborY = this.currentRegion.getY();
        switch (direction.getName()) {
            case "UP":
                neighborY--;
                break;
            case "DOWN":
                neighborY++;
                break;
            case "UPLEFT":
                neighborX--;
                neighborY--;
                break;
            case "UPRIGHT":
                neighborX++;
                neighborY--;
                break;
            case "DOWNLEFT":
                neighborX--;
                neighborY++;
                break;
            case "DOWNRIGHT":
                neighborX++;
                neighborY++;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
        return getRegion(neighborX, neighborY);
    }

    public Region getRegion(int x, int y) {
        // Implementation to retrieve a Region object with the specified x and y coordinates
        // For example:
        for (Region region : allRegions) {
            if (region.getX() == x && region.getY() == y) {
                return region;
            }
        }
        return null; // if no Region with the specified coordinates is found
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