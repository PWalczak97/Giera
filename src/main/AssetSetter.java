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
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 20 * gp.tileSize;
        gp.obj[0].worldY = 25 * gp.tileSize;

        gp.obj[2] = new OBJ_Doors(gp);
        gp.obj[2].worldX = 16 * gp.tileSize;
        gp.obj[2].worldY = 12 * gp.tileSize;

        gp.obj[3] = new OBJ_Chest(gp);
        gp.obj[3].worldX = 20 * gp.tileSize;
        gp.obj[3].worldY = 6 * gp.tileSize;

        gp.obj[4] = new OBJ_Boots(gp);
        gp.obj[4].worldX = 16 * gp.tileSize;
        gp.obj[4].worldY = 24 * gp.tileSize;
    }
}
