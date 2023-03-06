public class Player {
    public void done() {
//        this.isDone = true;
    }
    public void relocate(Region targetRegion) {
//        if (!targetRegion.belongsTo(this) || this.getBudget() < calculateRelocationCost(targetRegion)) {
//            return;
//        }
//        this.deductBudget(calculateRelocationCost(targetRegion));
//        this.currentRegion = targetRegion;
//        this.isDone = true;
    }
    private int calculateRelocationCost(Region targetRegion) {
//        int x = Math.abs(this.currentRegion.getX() - targetRegion.getX()) + Math.abs(this.currentRegion.getY() - targetRegion.getY());
//        return 5*x + 10;
    }
    public void move(Direction direction) {
//        Region targetRegion = this.currentRegion.getNeighbor(direction);
//        if (targetRegion == null || targetRegion.belongsTo(opponent)) {
//            this.deductBudget(1);
//            return;
//        }
//        this.currentRegion = targetRegion;
//        this.deductBudget(1);
//        this.isDone = true;
    }
    public void invest(int investmentAmount) {
//        if (this.getBudget() < investmentAmount + 1) {
//            this.deductBudget(1);
//            return;
//        }
//        int totalCost = investmentAmount + 1;
//        int depositToAdd = Math.min(investmentAmount, this.currentRegion.getMaxDeposit() - this.currentRegion.getDeposit());
//        this.currentRegion.addDeposit(depositToAdd);
//        this.deductBudget(totalCost);
//        this.isDone = true;
    }
    public void collect(int collectionAmount) {
//        if (this.getBudget() < 1 || collectionAmount > this.currentRegion.getDeposit()) {
//            this.deductBudget(1);
//            return;
//        }
//        this.addBudget(collectionAmount);
//        this.currentRegion.deductDeposit(collectionAmount);
//        if (this.currentRegion.getDeposit() == 0) {
//            this.currentRegion.setOwner(null);
//        }
//        this.deductBudget(1);
//        this.isDone = true;
    }
    public void shoot(Direction direction, int expenditure) {
//        Region targetRegion = this.currentRegion.getNeighbor(direction);
//        if (targetRegion == null) {
//            this.deductBudget(1);
//            return;
//        }
//        int totalCost = expenditure + 1;
//        if (this.getBudget() < totalCost) {
//            this.deductBudget(1);
//            return;
//        }
//        this.deductBudget(totalCost);
//        if (targetRegion.belongsTo(this)) {
//            targetRegion.setDeposit(Math.max(0, targetRegion.getDeposit() - expenditure));
//            if (targetRegion.getDeposit() < 1) {
//                targetRegion.setOwner(null);
//            }
//        } else if (!targetRegion.belongsTo(opponent)) {
//            // Unoccupied region, no effect
//        } else {
//            opponent.receiveAttack(targetRegion, expenditure);
//        }
//        this.isDone = true;
    }

}