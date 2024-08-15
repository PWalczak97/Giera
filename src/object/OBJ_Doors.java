package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Doors extends SuperObject{

    public OBJ_Doors(){

        name = "Doors";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/Doors.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        collision = true;
    }
}
