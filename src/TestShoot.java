import org.junit.Test;

public class TestShoot {
    Territory territory = new Territory();
    Player test = new Player("Player",territory,2,3,90000);

    @Test
    public void ShootUpTest(){
        for(int i = 0 ; i < 20 ; i++){
            test.playerInfo();
            test.shoot(Direction.UP,100000000);
            test.playerInfo();
            test.shoot(Direction.UP,-1);
            test.playerInfo();
            test.shoot(Direction.UP,87/7);
            test.playerInfo();
            test.shoot(Direction.UP,(10000000^10000000));
            test.playerInfo();
            test.shoot(Direction.UP,(10000000^10000000)*(-1));
            test.playerInfo();
            test.shoot(Direction.UP,0);
            test.playerInfo();
            test.shoot(Direction.UP,1000);
            test.playerInfo();
            test.shoot(Direction.UP,1000/0);
            test.playerInfo();
            test.move(Direction.UP);
        }
    }

    @Test
    public void ShootDownTest(){
        for(int i = 0 ; i < 20 ; i++){
            test.playerInfo();
            test.shoot(Direction.DOWN,100000000);
            test.playerInfo();
            test.shoot(Direction.DOWN,-1);
            test.playerInfo();
            test.shoot(Direction.DOWN,87/7);
            test.playerInfo();
            test.shoot(Direction.DOWN,(10000000^10000000));
            test.playerInfo();
            test.shoot(Direction.DOWN,(10000000^10000000)*(-1));
            test.playerInfo();
            test.shoot(Direction.DOWN,0);
            test.playerInfo();
            test.shoot(Direction.DOWN,1000);
            test.playerInfo();
            test.shoot(Direction.DOWN,1000/0);
            test.playerInfo();
            test.move(Direction.DOWN);

        }
    }

    @Test
    public void ShootUpRightTest(){
        for(int i = 0 ; i < 20 ; i++){
            test.playerInfo();
            test.shoot(Direction.UPRIGHT,100000000);
            test.playerInfo();
            test.shoot(Direction.UPRIGHT,-1);
            test.playerInfo();
            test.shoot(Direction.UPRIGHT,87/7);
            test.playerInfo();
            test.shoot(Direction.UPRIGHT,(10000000^10000000));
            test.playerInfo();
            test.shoot(Direction.UPRIGHT,(10000000^10000000)*(-1));
            test.playerInfo();
            test.shoot(Direction.UPRIGHT,0);
            test.playerInfo();
            test.shoot(Direction.UPRIGHT,1000);
            test.playerInfo();
            test.shoot(Direction.UPRIGHT,1000/0);
            test.playerInfo();
            test.move(Direction.UPRIGHT);
        }

    }

    @Test
    public void ShootUpLeftTest(){
        for(int i = 0 ; i < 20 ; i++){
            test.playerInfo();
            test.shoot(Direction.UPLEFT,100000000);
            test.playerInfo();
            test.shoot(Direction.UPLEFT,-1);
            test.playerInfo();
            test.shoot(Direction.UPLEFT,87/7);
            test.playerInfo();
            test.shoot(Direction.UPLEFT,(10000000^10000000));
            test.playerInfo();
            test.shoot(Direction.UPLEFT,(10000000^10000000)*(-1));
            test.playerInfo();
            test.shoot(Direction.UPLEFT,0);
            test.playerInfo();
            test.shoot(Direction.UPLEFT,1000);
            test.playerInfo();
            test.shoot(Direction.UPLEFT,1000/0);
            test.playerInfo();
            test.move(Direction.UPLEFT);
        }

    }

    @Test
    public void ShootDownLeftTest(){
        for(int i = 0 ; i < 20 ; i++){
            test.playerInfo();
            test.shoot(Direction.DOWNLEFT,100000000);
            test.playerInfo();
            test.shoot(Direction.DOWNLEFT,-1);
            test.playerInfo();
            test.shoot(Direction.DOWNLEFT,87/7);
            test.playerInfo();
            test.shoot(Direction.DOWNLEFT,(10000000^10000000));
            test.playerInfo();
            test.shoot(Direction.DOWNLEFT,(10000000^10000000)*(-1));
            test.playerInfo();
            test.shoot(Direction.DOWNLEFT,0);
            test.playerInfo();
            test.shoot(Direction.DOWNLEFT,1000);
            test.playerInfo();
            test.shoot(Direction.DOWNLEFT,1000/0);
            test.playerInfo();
            test.move(Direction.DOWNLEFT);
        }

    }
    @Test
    public void ShootDownRightTest(){
        for(int i = 0 ; i < 20 ; i++){
            test.playerInfo();
            test.shoot(Direction.DOWNRIGHT,100000000);
            test.playerInfo();
            test.shoot(Direction.DOWNRIGHT,-1);
            test.playerInfo();
            test.shoot(Direction.DOWNRIGHT,87/7);
            test.playerInfo();
            test.shoot(Direction.DOWNRIGHT,(10000000^10000000));
            test.playerInfo();
            test.shoot(Direction.DOWNRIGHT,(10000000^10000000)*(-1));
            test.playerInfo();
            test.shoot(Direction.DOWNRIGHT,0);
            test.playerInfo();
            test.shoot(Direction.DOWNRIGHT,1000);
            test.playerInfo();
            test.shoot(Direction.DOWNRIGHT,1000/0);
            test.playerInfo();
            test.move(Direction.DOWNRIGHT);
        }
    }
}
