package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_Zombie extends Entity {

    public MON_Zombie(GamePanel gp) {
        super(gp);

        type = 2;
        name = "Zombie";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defence = 0;
        exp = 2;

        getImage();
    }

    public void getImage(){
        up1 = setup("monster/zombie_back1");
        up2 = setup("monster/zombie_back2");
        down1 = setup("monster/zombie_front1");
        down2 = setup("monster/zombie_front2");
        left1 = setup("monster/zombie_left1");
        left2 = setup("monster/zombie_left2");
        right1 = setup("monster/zombie_right1");
        right2 = setup("monster/zombie_right2");
        standFront = setup("monster/zombie_stand");
        standBack = setup("monster/zombie_stand");
    }

    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    @Override
    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
