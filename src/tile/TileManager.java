package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("maps/map01.txt");
    }

    public void getTileImage(){

        setup(0, "Grass", false);
        setup(1, "Wall", true);
        setup(2, "Water", true);
        setup(3, "Dirt", false);
        setup(4, "Sand", false);
        setup(5, "Tree", true);
        setup(6, "Tree_apple", true);

    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] =  new Tile();
            tile[index].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public  void loadMap(String filePath){
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            if (is == null) {
                System.out.println("Map file not found: " + filePath);
            } else {
                System.out.println("Map file loaded successfully: " + filePath);
            }

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while (col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }

            br.close();
        } catch (Exception e){

        }
    }

    public void draw(Graphics2D g2){

       int col = 0;
       int row = 0;


       while(col < gp.maxWorldCol && row < gp.maxWorldRow){

           int tileNum = mapTileNum[col][row];
           int worldX = col * gp.tileSize;
           int worldY = row * gp.tileSize;
           int screenX = worldX - gp.player.worldX + gp.player.screenX;
           int screenY = worldY - gp.player.worldY + gp.player.screenY;

           if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                   worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

               g2.drawImage(tile[tileNum].image, screenX, screenY, null);
           }
           col++;

           if(col == gp.maxWorldCol){
               col = 0;
               row++;
           }
       }


    }
}
