package main;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, evemtRectDefaultY;

    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventRect= new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        evemtRectDefaultY = eventRect.y;
    }

    public void  checkEvent(){

        if(hit(27, 21, "right")){
            damagePit(gp.dialogueState);
        }
        if (hit(20, 21, "any")) {
            healingHeart(gp.dialogueState);
        }
    }

    public boolean hit(int eventCol, int eventRow, String requiredDirection){

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)){
            if(gp.player.direction.contentEquals(requiredDirection) || requiredDirection.contentEquals("any")){
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = evemtRectDefaultY;

        return hit;
    }

    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Wpadłeś w dziure";
        gp.player.life -= 1;
    }

    public void healingHeart(int gameState){
        if(gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Odzyskałeś zdrowie!";
            gp.player.life = gp.player.maxLife;
        }
    }

}
