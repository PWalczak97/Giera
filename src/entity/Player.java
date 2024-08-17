package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenY;
    public final int screenX;
    int hasKeys = 0;

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2- (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;



        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){

        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_back1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_back2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_front1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_front2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right2.png"));
            standFront = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_stand.png"));
            standBack = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_back_stand.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){

            if(keyH.upPressed == true){
                direction = "up";
            } else if(keyH.downPressed == true){
                direction = "down";
            } else if(keyH.leftPressed == true){
                direction = "left";
            } else if(keyH.rightPressed == true){
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.colissionCheckcer.checkTile(this);

            //CHECK OBJECT COLLISION
            int objectIndex = gp.colissionCheckcer.checkObject(this, true);
            pickUpObject(objectIndex);

            if(collisionOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 10){
                if(sprintNum == 1){
                    sprintNum = 2;
                } else if(sprintNum ==2){
                    sprintNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void pickUpObject(int i){

        if(i != 999){
            String objName = gp.obj[i].name;

            switch (objName){
                case "Key":
                    gp.playSoundEffect(1);
                    hasKeys++;
                    gp.obj[i] = null;
                    break;
                case "Doors":
                    if(hasKeys > 0){
                        gp.playSoundEffect(3);
                        gp.obj[i] = null;
                        hasKeys--;
                    }
                    break;
                case "Boots":
                    gp.playSoundEffect(2);
                    speed += 2;
                    gp.obj[i] = null;
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction){
            case "up":
                if(keyH.upPressed == false){
                    image = standBack;
                } else {
                    if(sprintNum == 1){
                        image = up1;
                    }
                    if(sprintNum == 2){
                        image = up2;
                    }
                }
                break;
            case "down":
                if(keyH.downPressed == false){
                    image = standFront;
                } else {
                    if(sprintNum == 1){
                        image = down1;
                    }
                    if(sprintNum == 2){
                        image = down2;
                    }
                }
                break;
            case "left":
                if(keyH.leftPressed == false){
                    image = left1;
                } else {
                    if(sprintNum == 1){
                        image = left1;
                    }
                    if(sprintNum == 2){
                        image = left2;
                    }
                }
                break;
            case "right":
                if(keyH.rightPressed == false){
                    image = right1;
                } else {
                    if(sprintNum == 1){
                        image = right1;
                    }
                    if(sprintNum == 2){
                        image = right2;
                    }
                }
                break;
        }

        g2.drawImage(image,screenX, screenY,gp.tileSize, gp.tileSize, null);
    }

}
