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

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2- (gp.tileSize/2);

        solidArea = new Rectangle(8, 16 ,32, 32);

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
                worldY -= speed;
            } else if(keyH.downPressed == true){
                direction = "down";
                worldY += speed;
            } else if(keyH.leftPressed == true){
                direction = "left";
                worldX -= speed;
            } else if(keyH.rightPressed == true){
                direction = "right";
                worldX += speed;
            }

            collisionOn = false;
            gp.colissionCheckcer.checkTile(this);

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
