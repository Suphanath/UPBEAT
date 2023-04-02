import java.lang.Math;

public class Region {
    private long deposit;
    private double interest;
    private double maxdeposit;
    private int rows;
    private int cols;
    private Player owner;
    private Region[] neighbors;
    private boolean isCityCenter;

    public Region(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.deposit = Configs.conf().start_deposit;
        this.interest = Configs.conf().interest_pct;
        this.owner = null;
        this.isCityCenter = false;
    }

    // getter and setter methods
    public int getRow() {
        return this.rows;
    }

    public int getCol() {
        return this.cols;
    }

    public void setCityCenterDeposit(int amount) {
        this.deposit = amount;
    }

    public void setCityCenter(boolean isCityCenter) {
        this.isCityCenter = isCityCenter;
    }

    public long getDeposit() {
        return this.deposit;
    }

    public double getInterest() {return this.interest;}

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
        return (this.rows + this.cols) * 10;
    }

    public boolean isCityCenter() {
        return this.isCityCenter;
    }
    // utility methods
    public void addDeposit(double amount) {
        this.deposit += amount;
    }

    public void deductDeposit(double amount) {
        this.deposit -= amount;
    }

    // method for calculating interest for the current turn
    public void calculateInterest(int turnCount) {
        if(turnCount == 1){
            this.interest = Configs.conf().interest_pct;
        } else {
            this.interest = Configs.conf().interest_pct * Math.log10(this.deposit) * Math.log(turnCount);
        }
    }
    public void calculateDeposit() {
        this.deposit += this.deposit * this.interest / 100;
    }
    public void calculateInvest(long amount, Player crew) {
        long maxDeposit = Configs.conf().max_dep;
        long newDeposit = deposit + amount;
        if (newDeposit > maxDeposit) {
            deposit = maxDeposit;
        } else {
            deposit = newDeposit;
        }
        if (owner == null) {
            setOwner(crew);
            owner.addRegion(this);
        }
    }
    public void receiveAttack(long expenditure) {
        if (this.owner == null) {
            return;
        }
        this.deposit -= expenditure;
        if (this.deposit <= 0) {
            this.deposit = 0;
            this.interest = Configs.conf().interest_pct;
            if (!isCityCenter) {
                owner.removeRegion(this);
                this.setOwner(null);
            }
        }
    }

    public long calculateCollect(long expenditure) {
        System.out.println("Current deposit: " + deposit);
        if (this.deposit - expenditure < 0)
            return 0;
        else if (this.deposit - expenditure == 0) {
            this.deposit = 0;
            this.interest = Configs.conf().interest_pct;
            this.owner.removeRegion(this);
            this.setOwner(null);
            return expenditure;
        } else {
            this.deposit = this.deposit - expenditure;
            return expenditure;
        }

    }

    public void regionInfo() {
        System.out.println("(" + rows + "," + cols + ")");
        System.out.println("Deposit: " + this.deposit);
        System.out.println("Owner: " + this.owner);
        System.out.println("Citycenter: " + (isCityCenter ? this.owner : "no"));
        System.out.println(" ");
    }
}
