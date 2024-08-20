package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Doors extends SuperObject{

    GamePanel gp;
    public OBJ_Doors(GamePanel gp){

        name = "Doors";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/Doors.png"));
            utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }

        collision = true;
    }
}
