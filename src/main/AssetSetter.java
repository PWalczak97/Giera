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
        int i = 0;
        gp.monster[i] = new MON_Zombie(gp);
        gp.monster[i].worldX = gp.tileSize*32;
        gp.monster[i].worldY = gp.tileSize*28;
        i++;

        gp.monster[i] = new MON_Zombie(gp);
        gp.monster[i].worldX = gp.tileSize*18;
        gp.monster[i].worldY = gp.tileSize*25;
        i++;

        gp.monster[i] = new MON_Zombie(gp);
        gp.monster[i].worldX = gp.tileSize*28;
        gp.monster[i].worldY = gp.tileSize*28;
        i++;

        gp.monster[i] = new MON_Zombie(gp);
        gp.monster[i].worldX = gp.tileSize*30;
        gp.monster[i].worldY = gp.tileSize*26;
        i++;

        gp.monster[i] = new MON_Zombie(gp);
        gp.monster[i].worldX = gp.tileSize*35;
        gp.monster[i].worldY = gp.tileSize*20;
        i++;
    }
}
