import org.junit.Test;

public class TestCanbought {
    @Test
    public void Pay(){
        Territory territory = new Territory();
        Player test = new Player("Player",territory,5,7,2000);
        test.playerInfo();
        test.move(Direction.UPRIGHT);

        test.canBought(100000000);
        test.playerInfo();
        test.newTurn();

        test.canBought(-1);
        test.playerInfo();
        test.newTurn();

        test.canBought(87/7);
        test.playerInfo();
        test.newTurn();

        test.canBought(10000000^10000000);
        test.playerInfo();
        test.newTurn();

        test.canBought((10000000^10000000)*(-1));
        test.playerInfo();
        test.newTurn();

        test.canBought(0);
        test.playerInfo();
        test.newTurn();

        test.canBought(1000);
        test.playerInfo();
        test.newTurn();

        test.canBought(2000/0);
        test.playerInfo();
        test.newTurn();
    }
}