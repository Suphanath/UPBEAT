import org.junit.Test;

public class TestInvest {
    @Test
    public void InvestRegion(){
        Territory territory = new Territory();

        Player test = new Player("Player",territory,4,5,1000);
        test.playerInfo();
        test.move(Direction.UPRIGHT);

        test.invest(100000000);
        test.playerInfo();
        test.newTurn();

        test.invest(-1);
        test.playerInfo();
        test.newTurn();

        test.invest(87/7);
        test.playerInfo();
        test.newTurn();

        test.invest(10000000^10000000);
        test.playerInfo();
        test.newTurn();

        test.invest((10000000^10000000)*(-1));
        test.playerInfo();
        test.newTurn();

        test.invest(0);
        test.playerInfo();
        test.newTurn();

        test.invest(1000);
        test.playerInfo();
        test.newTurn();

//        test.invest(2000/0);
//        test.playerInfo();
//        test.newTurn();
    }
    @Test
    public void InvestOpponentRegion(){
        Territory territory = new Territory();
        Player test = new Player("Player",territory,4,5,1000);
        System.out.println(test.opponent());

        test.playerInfo();
        test.move(Direction.DOWNRIGHT);
        test.playerInfo();

        test.invest(100000000);
        test.playerInfo();
        test.newTurn();

        test.invest(-1);
        test.playerInfo();
        test.newTurn();

        test.invest(87/7);
        test.playerInfo();
        test.newTurn();

        test.invest(10000000^10000000);
        test.playerInfo();
        test.newTurn();

        test.invest((10000000^10000000)*(-1));
        test.playerInfo();
        test.newTurn();

        test.invest(0);
        test.playerInfo();
        test.newTurn();

        test.invest(1000);
        test.playerInfo();
        test.newTurn();

//        test.invest(2000/0);
//        test.playerInfo();
//        test.newTurn();
    }
    @Test
    public void InvestNoOwnerRegion(){
        Territory territory = new Territory();
        Player test = new Player("Player",territory,4,5,1000);
        test.playerInfo();
        test.move(Direction.DOWNRIGHT);
        test.playerInfo();

        test.invest(100000000);
        test.playerInfo();
        test.newTurn();

        test.invest(-1);
        test.playerInfo();
        test.newTurn();

        test.invest(87/7);
        test.playerInfo();
        test.newTurn();

        test.invest(10000000^10000000);
        test.playerInfo();
        test.newTurn();

        test.invest((10000000^10000000)*(-1));
        test.playerInfo();
        test.newTurn();

        test.invest(0);
        test.playerInfo();
        test.newTurn();

        test.invest(1000);
        test.playerInfo();
        test.newTurn();

//        test.invest(2000/0);
//        test.playerInfo();
//        test.newTurn();
    }
}
