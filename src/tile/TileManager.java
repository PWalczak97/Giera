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

        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("maps/map01.txt");
    }

    public void getTileImage(){

        //PLACEHOLDERS
        setup(0, "Grass", false);
        setup(1, "Grass", false);
        setup(2, "Grass", false);
        setup(3, "Grass", false);
        setup(4, "Grass", false);
        setup(5, "Grass", false);
        setup(6, "Grass", false);
        setup(7, "Grass", false);
        setup(8, "Grass", false);
        setup(9, "Grass", false);


        setup(10, "Grass", false);
        setup(11, "Grass2", false);
        setup(12, "Dirt_Bot", false);
        setup(13, "Dirt_Bot_left", false);
        setup(14, "Dirt_Bot_Right", false);
        setup(15, "Dirt_Corner1", false);
        setup(16, "Dirt_Corner2", false);
        setup(17, "Dirt_Corner3", false);
        setup(18, "Dirt_Corner4", false);
        setup(19, "Dirt_Left", false);
        setup(20, "Dirt_Right", false);
        setup(21, "Dirt_Top", false);
        setup(22, "Dirt_Top_Left", false);
        setup(23, "Dirt_Top_Right", false);
        setup(24, "Dirt", false);
        setup(25, "Sand", false);
        setup(26, "Tree", true);
        setup(27, "Tree_apple", true);
        setup(28, "Wall", true);
        setup(29, "Water", true);
        setup(30, "Water_Top", true);
        setup(31, "Water_Bot_Left", true);
        setup(32, "Water_Bot_Right", true);
        setup(33, "Water_Corner1", true);
        setup(34, "Water_Corner2", true);
        setup(35, "Water_Corner3", true);
        setup(36, "Water_Corner4", true);
        setup(37, "Water_Left", true);
        setup(38, "Water_Right", true);
        setup(39, "Water_Bot", true);
        setup(40, "Water_Top_Left", true);
        setup(41, "Water_Top_Right", true);


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
