package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Doors;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 24 * gp.tileSize;
        gp.obj[0].worldY = 8 * gp.tileSize;

        gp.obj[2] = new OBJ_Doors();
        gp.obj[2].worldX = 35 * gp.tileSize;
        gp.obj[2].worldY = 26 * gp.tileSize;

        gp.obj[3] = new OBJ_Chest();
        gp.obj[3].worldX = 40 * gp.tileSize;
        gp.obj[3].worldY = 24 * gp.tileSize;

        gp.obj[4] = new OBJ_Boots();
        gp.obj[4].worldX = 16 * gp.tileSize;
        gp.obj[4].worldY = 24 * gp.tileSize;
    }
}
