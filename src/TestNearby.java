import org.junit.Test;

public class TestNearby {
    Territory territory = new Territory();
    Player test = new Player("Player",territory,3,4,2000);

    @Test
    public void nearbyUpTest(){
        for(int i = 0 ; i < 20 ; i++){
            test.nearby(Direction.UP);
            test.move(Direction.UP);
        }
    }
    @Test
    public void nearbyDownTest(){
        for(int i = 0 ; i < 20 ; i++){
            test.nearby(Direction.DOWN);
            test.move(Direction.DOWN);
        }
    }
    @Test
    public void nearbyUpLeftTest(){
        for(int i = 0 ; i < 20 ; i++){
            test.nearby(Direction.UPLEFT);
            test.move(Direction.UPLEFT);
        }
    }
    @Test
    public void nearbyUpRightTest(){
        for(int i = 0 ; i < 20 ; i++){
            test.nearby(Direction.UPRIGHT);
            test.move(Direction.UPRIGHT);
        }
    }
    @Test
    public void nearbyDownLeftTest(){
        for(int i = 0 ; i < 20 ; i++){
            test.nearby(Direction.DOWNLEFT);
            test.move(Direction.DOWNLEFT);
        }
    }
    @Test
    public void nearbyDownRightTest(){
        for(int i = 0 ; i < 20 ; i++){
            test.nearby(Direction.DOWNRIGHT);
            test.move(Direction.DOWNRIGHT);
        }
    }
}
