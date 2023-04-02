import org.junit.Test;

public class TestMove {
    Territory territory = new Territory();
    Player player = new Player("Player",territory,5,3,1000);

    public void delay(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            return;
        }
    }

    @Test
    public void MoveUpTest(){
        player.playerInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move(Direction.UP);
        }
        player.move(Direction.DOWN);
    }

    @Test
    public void MoveDownTest(){
        player.playerInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move(Direction.DOWN);
        }
        player.move(Direction.UP);
    }

    @Test
    public void MoveUprightTest(){
        player.playerInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move(Direction.UPRIGHT);
        }
    }

    @Test
    public void MoveUpLeftTest(){
        player.playerInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move(Direction.UPLEFT);
        }
    }

    @Test
    public void MoveDownLeftTest(){
        player.playerInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move(Direction.DOWNLEFT);
        }
    }
    @Test
    public void MoveDownRightTest(){
        player.playerInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.move(Direction.DOWNRIGHT);
        }
    }

    @Test
    public void RandomMove(){
        player.playerInfo();
        for(int i = 0 ; i < 20 ; i++){
            player.randomMove();
        }
    }
    @Test
    public void NotEnoughBudgetToMove(){
        Player player = new Player("Playerr",territory,9,9,0);
        player.playerInfo();
        player.move(Direction.UP);
    }
}
