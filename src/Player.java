import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Player {
    private boolean isDone = false;
    private boolean win = false;
    private long budget;
    private final long[] pos = new long[2];
    private final long[] cityPos = new long[2];
    private final Set<Region> ownedRegions = new HashSet<>();
    private String name;
    private Territory territory;
    private Region currentRegion;
    private Region region;
    List<Region> allRegions = new ArrayList<>();
    protected Map<String, Long> variables;

    public Player(String name, Territory territory) {
        this.name = name;
        this.territory = territory;
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        long startRow = rand.nextLong(territory.row());
        long startCol = rand.nextLong(territory.col());
        while (territory.region(new long[]{startRow, startCol}).getOwner() != null) {
            startRow = rand.nextLong(territory.row());
            startCol = rand.nextLong(territory.col());
        }
        pos[0] = startRow;
        pos[1] = startCol;
        cityPos[0] = pos[0];
        cityPos[1] = pos[1];
        territory.region(cityPos).setCityCenter(true);
        territory.region(cityPos).setOwner(this);
        territory.region(cityPos).setCityCenterDeposit(Configs.conf().init_center_dep);
        ownedRegions.add(territory.region(cityPos));
        this.budget = Configs.conf().init_budget;
    }
    private long[] randomStartingPoint(Territory territory, ThreadLocalRandom rand) {
        long startRow = rand.nextLong(territory.row());
        long startColumn = rand.nextLong(territory.col());
        while (territory.region(new long[]{startRow, startColumn}).getOwner() != null) {
            startRow = rand.nextLong(territory.row());
            startColumn = rand.nextLong(territory.col());
        }
        return new long[]{startRow, startColumn};
    }
    private void setCityCenter(Region region) {
        region.setCityCenter(true);
    }
    private void setCityCenterDeposit(Region region) {
        region.setCityCenterDeposit(Configs.conf().init_center_dep);
    }
    private void setOwner(Region region) {
        region.setOwner(this);
        ownedRegions.add(region);
    }

    public int opponent() {
        int maxDistance = 6;
        for (int i = 1; i <= maxDistance; i++) {
            for (int j = 1; j <= 6; j++) {
                Direction direction;
                if (j == 1) {
                    direction = Direction.UP;
                } else if (j == 2) {
                    direction = Direction.UPRIGHT;
                } else if (j == 3) {
                    direction = Direction.DOWNRIGHT;
                } else if (j == 4) {
                    direction = Direction.DOWN;
                } else if (j == 5) {
                    direction = Direction.DOWNLEFT;
                } else {
                    direction = Direction.UPLEFT;
                }
                long[] nextPos = searchPosition(i, pos, direction);
                if (isBound(nextPos) && territory.region(nextPos).getOwner() != this && isOpponent(nextPos)) {
                    return i * 10 + j;
                }
            }
        }
        return 0;
    }

    public long[] searchPosition(int distance, long[] startPos, Direction direction) {
        long[] curPos = startPos.clone();
        for (int i = 1; i <= distance; i++) {
            curPos = nextPos(curPos, direction);
        }
        return curPos;
    }

    public long nearby(Direction direction) {
        long[] nearbyOpponent = pos;
        for (int i = 1; i < Configs.conf().m * Configs.conf().n; i++) {
            nearbyOpponent = nextPos(nearbyOpponent, direction);
            if (isOpponent(nearbyOpponent)) {
                long currentdeposit = territory.region(nearbyOpponent).getDeposit();
                int depositDigits = 0;
                while (currentdeposit != 0) {
                    currentdeposit /= 10;
                    depositDigits++;
                }
                return 100L * i + depositDigits;
            }
        }
        return 0;
    }

    private boolean isOpponent(long[] position) {
        if (!isBound(position)) { return false;}
        return territory.region(position).getOwner() != null && territory.region(position).getOwner() != this;
    }

    public boolean canBought(long cost) {
        if (budget >= cost) {
            budget -= cost;
            return true;
        } else {
            System.out.println("Sorry, you do not have enough budget for this transaction.");
            done();
            return false;
        }
    }

    private boolean isBound(long[] pos) {
        return IntStream.range(0, 2)
                .allMatch(i -> pos[i] >= 0 && pos[i] < (i == 0 ? territory.row() : territory.col()));
    }

    public long[] nextPos(long[] curPos, Direction direction) {
        long[] nextPos = new long[2];
        if (direction == Direction.UP) {
            nextPos[0] = curPos[0] - 1;
            nextPos[1] = curPos[1];
        } else if (direction == Direction.DOWN) {
            nextPos[0] = curPos[0] + 1;
            nextPos[1] = curPos[1];
        } else if (direction == Direction.UPRIGHT) {
            if (curPos[1] % 2 == 0) {
                nextPos[0] = curPos[0] - 1;
            } else {
                nextPos[0] = curPos[0];
            }
            nextPos[1] = curPos[1] + 1;
        } else if (direction == Direction.UPLEFT) {
            if (curPos[1] % 2 == 0) {
                nextPos[0] = curPos[0] - 1;
            } else {
                nextPos[0] = curPos[0];
            }
            nextPos[1] = curPos[1] - 1;
        } else if (direction == Direction.DOWNRIGHT) {
            if (curPos[1] % 2 == 0) {
                nextPos[0] = curPos[0];
            } else {
                nextPos[0] = curPos[0] + 1;
            }
            nextPos[1] = curPos[1] + 1;
        } else if (direction == Direction.DOWNLEFT) {
            if (curPos[1] % 2 == 0) {
                nextPos[0] = curPos[0];
            } else {
                nextPos[0] = curPos[0] + 1;
            }
            nextPos[1] = curPos[1] - 1;
        }
        return nextPos;
    }


    public void move(Direction direction) {
        if (isDone || !canBought(1)) {
            return;
        }
        long[] nextPos = nextPos(pos, direction);
        if (!isBound(nextPos)) {
            return;
        }
        pos[0] = nextPos[0];
        pos[1] = nextPos[1];
        this.playerInfo();
    }

    public void randomMove() {
        if (isDone) {
            return;
        }
        move(Direction.values()[(int) (Math.random() * Direction.values().length)]);
    }

    public void invest(long expenditure) {
        if (isDone || !canBought(1)) {
            return;
        }
        Region region = territory.region(pos);
        if (region.getOwner() != null && region.getOwner() != this) {
            System.out.println("Investing in this region is not possible as it has already been occupied by other.");
            return;
        }
        if (canBought(expenditure)) {
            region.calculateInvest(expenditure, this);
            this.done();
        }
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
        int x = Math.abs(this.currentRegion.getRow() - targetRegion.getRow()) + Math.abs(this.currentRegion.getCol() - targetRegion.getCol());
        return 5*x + 10;
    }

    public void collect(int expenditure) {
        if (isDone || !canBought(1)) {
            return;
        }
        Region currentRegion = territory.region(pos);
        if (currentRegion.getOwner() != this) {
            System.out.println("This is not "+ name + " region");
            return;
        }
        budget += currentRegion.calculateCollect(expenditure);
        done();
    }

    public void shoot(Direction direction, long expenditure) {
        if (isDone || !canBought(1) || !canBought(expenditure)) {
            return;
        }
        long[] shootDirection = nextPos(pos, direction);
        if (!isBound(shootDirection)) {
            return;
        }
        Region region = territory.region(shootDirection);
        if (region.getOwner() == null) {
            System.out.println("Can not shoot region unowned");
            return;
        }
        region.receiveAttack(expenditure);
        if (region.getDeposit() == 0 && region.isCityCenter()) {
            Player owner = region.getOwner();
            if (owner == this) {
                owner.lose();
            } else {
                owner.removeRegion(region);
                if (owner.getOwnedRegions().isEmpty()) {
                    owner.lose();
                }
            }
            region.setOwner(null);
            region.setCityCenter(false);
        }
        done();
    }

    public long getBudget() {
        return this.budget;
    }

    public void deductBudget(int amount) {
        this.budget -= amount;
    }

    public void addBudget(int amount) {
        this.budget += amount;
    }

    public Set<Region> getOwnedRegions() {
        return ownedRegions;
    }

    public void removeRegion(Region r) {
        ownedRegions.remove(r);
    }

    public void addRegion(Region r) {
        ownedRegions.add(r);
    }

    public void lose() {
        win = false;
    }

    public void newTurn() {
        isDone = false;
    }

    public void playerInfo() {
        String message = String.format("Name: %s \nBudget: %d \nOwned regions: %d", this.name, this.budget, ownedRegions.size());
        String positionMessage = String.format("Position: (%d, %d)", pos[0], pos[1]);
        System.out.println(message);
        System.out.println(positionMessage);
    }

    public Player(String name, Territory territory, int row, int column, int budget) {
        this.name = name;
        this.territory = territory;
        pos[0] = row;
        pos[1] = column;
        cityPos[0] = row;
        cityPos[1] = column;
        territory.region(cityPos).setCityCenter(true);
        territory.region(cityPos).setOwner(this);
        territory.region(cityPos).setCityCenterDeposit(Configs.conf().init_center_dep);
        ownedRegions.add(territory.region(cityPos));
        this.budget = budget;
    }
}