package main;

import entity.NPC_Oldman;
import monster.MON_Zombie;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        gp.obj[0] = new OBJ_Heart(gp);
        gp.obj[0].worldX = gp.tileSize*20;
        gp.obj[0].worldY = gp.tileSize*21;

        gp.obj[1] = new OBJ_Doors(gp);
        gp.obj[1].worldX = gp.tileSize*18;
        gp.obj[1].worldY = gp.tileSize*21;
    }

    public void setNPC(){

        gp.npc[0] = new NPC_Oldman(gp);
        gp.npc[0].worldX = gp.tileSize*18;
        gp.npc[0].worldY = gp.tileSize*19;
    }

    public void setMonster(){
        gp.monster[0] = new MON_Zombie(gp);
        gp.monster[0].worldX = gp.tileSize*32;
        gp.monster[0].worldY = gp.tileSize*28;

        gp.monster[1] = new MON_Zombie(gp);
        gp.monster[1].worldX = gp.tileSize*18;
        gp.monster[1].worldY = gp.tileSize*25;
    }
}
