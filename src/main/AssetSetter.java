package main;

import entity.NPC_Oldman;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

    }

    public void setNPC(){

        gp.npc[0] = new NPC_Oldman(gp);
        gp.npc[0].worldX = gp.tileSize*30;
        gp.npc[0].worldY = gp.tileSize*21;

        gp.obj[1] = new OBJ_Heart(gp);
        gp.obj[1].worldX = gp.tileSize*20;
        gp.obj[1].worldY = gp.tileSize*21;

    }
}
