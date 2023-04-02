import org.junit.Test;

public class TestCollect {
    @Test
    public void collectRegion(){
        Territory territory = new Territory();
        Player test = new Player("Player",territory,5,7,2000);
        test.playerInfo();
        test.move(Direction.UPRIGHT);

        test.collect(100000000);
        test.playerInfo();
        test.newTurn();

        test.collect(-1);
        test.playerInfo();
        test.newTurn();

        test.collect(87/7);
        test.playerInfo();
        test.newTurn();

        test.collect(10000000^10000000);
        test.playerInfo();
        test.newTurn();

        test.collect((10000000^10000000)*(-1));
        test.playerInfo();
        test.newTurn();

        test.collect(0);
        test.playerInfo();
        test.newTurn();

        test.collect(1000);
        test.playerInfo();
        test.newTurn();

//        test.collect(2000/0);
//        test.playerInfo();
//        test.newTurn();

    }
    @Test
    public void collectOpponentRegion(){
        Territory territory = new Territory();
        Player test = new Player("Player",territory,4,1,500);
        System.out.println(test.opponent());

        test.playerInfo();
        test.move(Direction.DOWNRIGHT);
        test.playerInfo();

        test.collect(100000000);
        test.playerInfo();
        test.newTurn();

        test.collect(-1);
        test.playerInfo();
        test.newTurn();

        test.collect(87/7);
        test.playerInfo();
        test.newTurn();

        test.collect(10000000^10000000);
        test.playerInfo();
        test.newTurn();

        test.collect((10000000^10000000)*(-1));
        test.playerInfo();
        test.newTurn();

        test.collect(0);
        test.playerInfo();
        test.newTurn();

        test.collect(1000);
        test.playerInfo();
        test.newTurn();

//        test.collect(2000/0);
//        test.playerInfo();
//        test.newTurn();
    }
    @Test
    public void collectNoOwnerRegion(){
        Territory territory = new Territory();
        Player test = new Player("Player",territory,6,6,1000);
        test.playerInfo();
        test.move(Direction.DOWNRIGHT);
        test.playerInfo();

        test.collect(100000000);
        test.playerInfo();
        test.newTurn();

        test.collect(-1);
        test.playerInfo();
        test.newTurn();

        test.collect(87/7);
        test.playerInfo();
        test.newTurn();

        test.collect(10000000^10000000);
        test.playerInfo();
        test.newTurn();

        test.collect((10000000^10000000)*(-1));
        test.playerInfo();
        test.newTurn();

        test.collect(0);
        test.playerInfo();
        test.newTurn();

        test.collect(1000);
        test.playerInfo();
        test.newTurn();

//        test.collect(2000/0);
//        test.playerInfo();
//        test.newTurn();
    }
}
