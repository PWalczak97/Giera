package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    public GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, standFront, standBack;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction = "down";

    public int  spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public boolean attacking = false;
    public boolean alive = true;
    public  boolean dying = false;
    int dyingCounter = 0;
    boolean hpBanOn = false;
    int hpBarCounter = 0;

    String dialogues[] = new String[20];
    int dialogueIndex = 0;

    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int type;  // 0 == player, 1 == npc, 2 == monster

    //Character Status
    public int maxLife;
    public int life;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction() {

    }

    public void damageReaction(){

    }

    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update(){
        setAction();

        collisionOn = false;
        gp.colissionCheckcer.checkTile(this);
        gp.colissionCheckcer.checkObject(this, false);
        gp.colissionCheckcer.checkEntity(this, gp.npc);
        gp.colissionCheckcer.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.colissionCheckcer.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                gp.playSoundEffect(5);
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

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
            if(spriteNum == 1){
                spriteNum = 2;
            } else if(spriteNum ==2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 30){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

            switch (direction){
                case "up":
                        if(spriteNum == 1){
                            image = up1;
                        }
                        if(spriteNum == 2){
                            image = up2;
                        }
                    break;
                case "down":
                        if(spriteNum == 1){
                            image = down1;
                        }
                        if(spriteNum == 2){
                            image = down2;
                        }

                    break;
                case "left":
                        if(spriteNum == 1){
                            image = left1;
                        }
                        if(spriteNum == 2){
                            image = left2;
                        }

                    break;
                case "right":
                        if(spriteNum == 1){
                            image = right1;
                        }
                        if(spriteNum == 2){
                            image = right2;
                        }

                    break;
            }

            //MONSTER HP BAR
            if(type == 2 && hpBanOn == true){
                double oneScale = (double) gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;
                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 6);
                g2.setColor(new Color(255, 0 ,30));
                g2.fillRect(screenX,screenY - 15, (int)hpBarValue, 5);

                hpBarCounter++;

                if(hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBanOn = false;
                }
            }

            if(invincible == true){
                hpBanOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if(dying == true){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            changeAlpha(g2, 1f);
        }
    }

    private void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int interval = 5;

        if(dyingCounter <= interval){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > interval && dyingCounter <= interval*2){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > interval*2 && dyingCounter <= interval*3){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > interval*3 && dyingCounter <= interval*4){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > interval*4 && dyingCounter <= interval*5){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > interval*5 && dyingCounter <= interval*6){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > interval*6 && dyingCounter <= interval*7){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > interval*7 && dyingCounter <= interval*8){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > interval*8){
            dying = false;
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath){

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath + ".png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public BufferedImage setup(String imagePath, int width, int height){

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath + ".png"));
            image = utilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }



}
