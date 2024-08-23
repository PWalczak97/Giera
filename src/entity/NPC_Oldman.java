package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Oldman extends Entity{

    public NPC_Oldman(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
    }

    public void getImage(){

        up1 =setup("npc/oldman_back1");
        up2 =setup("npc/oldman_back2");
        down1 =setup("npc/oldman_front1");
        down2 =setup("npc/oldman_front2");
        left1 =setup("npc/oldman_left1");
        left2 =setup("npc/oldman_left2");
        right1 =setup("npc/oldman_right1");
        right2 =setup("npc/oldman_right2");
        standFront =setup("npc/oldman_stand");
        standBack =setup("npc/oldman_back_stand");
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


}
