package entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    KeyHandler keyH;

    public final int screenY;
    public final int screenX;

    public Player(GamePanel gp, KeyHandler keyH){

        super(gp);
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

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        level = 1;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        maxLife = 6;
        life = maxLife;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = getAttact();
        defence = getDefence();
    }

    private int getDefence() {
        return attack = strength * currentWeapon.attackValue;
    }

    private int getAttact() {
        return defence = dexterity * currentShield.defenceValue;
    }

    public void getPlayerImage(){

        up1 = setup("player/boy_back1");
        up2 = setup("player/boy_back2");
        down1 = setup("player/boy_front1");
        down2 = setup("player/boy_front2");
        left1 = setup("player/boy_left1");
        left2 = setup("player/boy_left2");
        right1 = setup("player/boy_right1");
        right2 = setup("player/boy_right2");
        standFront = setup("player/boy_stand");
        standBack = setup("player/boy_back_stand");
    }

    public void getPlayerAttackImage(){

        attackUp1 = setup("player/boy_attack_back1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("player/boy_attack_back2",gp.tileSize,gp.tileSize*2);
        attackDown1 = setup("player/boy_attack_front1",gp.tileSize,gp.tileSize*2);
        attackDown2 = setup("player/boy_attack_front2",gp.tileSize,gp.tileSize*2);
        attackLeft1 = setup("player/boy_attack_left1",gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("player/boy_attack_left2",gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("player/boy_attack_right1",gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("player/boy_attack_right2",gp.tileSize*2, gp.tileSize);
    }

    public void update(){

        if(attacking){

            attacking();

        } else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed || keyH.spacePressed){

            if(keyH.upPressed){
                direction = "up";
            } else if(keyH.downPressed){
                direction = "down";
            } else if(keyH.leftPressed){
                direction = "left";
            } else if(keyH.rightPressed){
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.colissionCheckcer.checkTile(this);

            //CHECK OBJECT COLLISION
            int objectIndex = gp.colissionCheckcer.checkObject(this, true);
            pickUpObject(objectIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.colissionCheckcer.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.colissionCheckcer.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            //CHECK EVENT
            gp.eventHandler.checkEvent();

            if(collisionOn == false && keyH.enterPressed == false){
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

            gp.keyH.enterPressed = false;

            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if(spriteNum ==2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public void attacking(){

        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction){
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            solidArea.width = gp.tileSize;
            solidArea.height = gp.tileSize;

            int monsterIndex = gp.colissionCheckcer.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i){

        if(i != 999){

        }
    }

    public void interactNPC(int i){
        if(i != 999){

            if(gp.keyH.enterPressed){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        } else {
            if(gp.keyH.spacePressed){
                gp.playSoundEffect(6);
                attacking = true;
            }
        }
    }


    private void contactMonster(int i) {
        if(i != 999){
            if(invincible == false){
                gp.playSoundEffect(5);
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i){

        if(i != 999){
            if(gp.monster[i].invincible == false){
                gp.playSoundEffect(7);
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if(gp.monster[i].life <= 0){
                    gp.monster[i].dying = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int tempScreenX =  screenX;
        int tempScreenY = screenY;

        switch (direction){
            case "up":
                if(attacking == false){
                    if(keyH.upPressed == false){
                            image = standBack;
                        } else {
                            if(spriteNum == 1){
                                image = up1;
                            }
                            if(spriteNum == 2){
                                image = up2;
                            }
                    }
                } else {
                    tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1){
                        image = attackUp1;
                    }
                    if(spriteNum == 2){
                        image = attackUp2;
                    }
                }

                break;
            case "down":
                if(attacking == false){
                    if(keyH.downPressed == false){
                        image = standFront;
                    } else {
                        if(spriteNum == 1){
                            image = down1;
                        }
                        if(spriteNum == 2){
                            image = down2;
                        }
                    }
                } else {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }

                break;
            case "left":
                if(attacking == false){
                    if(keyH.leftPressed == false){
                        image = left1;
                    } else {
                        if(spriteNum == 1){
                            image = left1;
                        }
                        if(spriteNum == 2){
                            image = left2;
                        }
                    }
                } else {
                    tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 1){
                        image = attackLeft1;
                    }
                    if(spriteNum == 2){
                        image = attackLeft2;
                    }
                }

                break;
            case "right":
                if(attacking == false){
                    if(keyH.rightPressed == false){
                        image = right1;
                    } else {
                        if(spriteNum == 1){
                            image = right1;
                        }
                        if(spriteNum == 2){
                            image = right2;
                        }
                    }
                } else {
                    if(spriteNum == 1){
                        image = attackRight1;
                    }
                    if(spriteNum == 2){
                        image = attackRight2;
                    }
                }

                break;
        }

        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image,tempScreenX, tempScreenY,null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

}
