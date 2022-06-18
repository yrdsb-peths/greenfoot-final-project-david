import greenfoot.*;
import java.util.*;

/**
 * The playable character's class. It manages all collision detection and movement. 
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Player extends ScrollActor
{
    // Movement keybinds
    public static String jump = "up";
    public static String left = "left";
    public static String right = "right";
    public static String dash = "0";
    // Movement speed-related values. Positive is to the right (for x) or down (for y)
    private int vY = 0;
    private int vX = 0;
    public static int speed = 5;
    private int jumpVelocity = -12;
    public static double g = 1;
    private int bounceVY = 0;
    // The displacement of the player relative to a certain location (usually spanw point)
    public int movedX = 0;
    public int movedY = 0;
    private int platformDY = 0;
    public static int saveX = 0;
    public static int saveY = 0;
    // Values for movement restrictions or collision restrictions
    private boolean canHead = true;
    private boolean jumped = false;
    private boolean isDashing = false;
    private int markFrame = 0;
    private int isDashingCDTracker = 0;
    public static boolean canJump = true;
    public static boolean warpedToCheckpoint = false;
    // Sprite manangement variables
    private int dir = 1;
    private int frames = 0;
    // Player sprites
    private static GreenfootImage[] run = new GreenfootImage[6];
    private static GreenfootImage[] runL = new GreenfootImage[6];
    private static GreenfootImage[] idle = new GreenfootImage[4];
    private static GreenfootImage[] idleL = new GreenfootImage[4];
    private static GreenfootImage[] fall = new GreenfootImage[2];
    private static GreenfootImage[] fallL = new GreenfootImage[2];
    private static GreenfootImage[] jumpS = new GreenfootImage[4];
    private static GreenfootImage[] jumpSL = new GreenfootImage[4];
    private static GreenfootImage jumpSprite = new GreenfootImage("jump3.png");
    // Game system variables
    public static boolean paused = false;
    public static boolean help = false;
    public static int level = 0;
    public static Levels[] levels = {new Level1(),new Level2(), new Level3(),new Level4(), new Level5(), new Level6()};
    private static int playerCount = 0;
    public static boolean cheatsOn = false;

    /**
     * Constructs a player object and sets up the arrays of sprites with proper images
     */
    public Player(){
        super();
        // Sprite setup
        if(playerCount == 0){
            for(int i = 0; i < 6; i++){
                run[i] = new GreenfootImage("run" + (i + 1) + ".png");
                runL[i] = new GreenfootImage("run" + (i + 1) + ".png");
                runL[i].mirrorHorizontally();
            }
            for(int i = 0; i < 4; i++){
                idle[i] = new GreenfootImage("idle" + (i+1) + ".png");
                jumpS[i] = new GreenfootImage("jump" + (i+5) + ".png");
                idleL[i] = new GreenfootImage("idle" + (i+1) + ".png");
                jumpSL[i] = new GreenfootImage("jump" + (i+5) + ".png");
                idleL[i].mirrorHorizontally();
                jumpSL[i].mirrorHorizontally();
            }
            for(int i = 0; i < 2; i++){
                fall[i] = new GreenfootImage("fall" + (i+1) + ".png");
                fallL[i] = new GreenfootImage("fall" + (i+1) + ".png");
                fallL[i].mirrorHorizontally();
            }
        }
        playerCount++;
    }

    public void act()
    {
        if(!paused){
            setLevelRestrictions();
            collisionCheckY();
            
            // To prevent dashing on ice
            if(touchingIce()){
                isDashingCDTracker = frames;
            }
            
            // For x velocity while dashing
            if(isDashing && vX != 0){
                vX -= dir*2;
                vY = 0;
            }
            if(isDashing && ((vX < 0 && Greenfoot.isKeyDown(right)) || vX > 0 && Greenfoot.isKeyDown(left))){
                vX = 0;
            }
            if(vX == 0){
                isDashing = false;
            }
            
            movement();
            setDir();
            
            // Movement and collision checking
            getWorld().moveCam(vX,0);
            checkStuckX();
            getWorld().moveCam(0,vY);
            bounceVY = vY;
            movedY += vY;
            platformDY += vY;
            checkStuckY();
            bounce();
            
            animate();
            
            // Location tracking
            movedX += vX;
            if(!help){
                saveX += vX;
                saveY += vY;
            }
            
            // Changing x velocity at the end of movement based on whether the player is touching ice
            if(!isDashing && !touchingIce()){
                vX = 0;
            }
            if(touchingIce() && vX != 0 && !(Greenfoot.isKeyDown(left) || Greenfoot.isKeyDown(right)) && frames%4 == 0 && !isDashing){
                vX -= dir;
            }
            
            if(cheatsOn){
                vY = 0;
            }
            frames++;
            warpToCheckpoint();
        }
    }

    /**
     * Checks if the player is hitting blocks in the y direction
     */
    public void collisionCheckY(){
        if(ground()){
            vY = 0;
            platformDY = 0;
            markFrame = frames;
            if(!touchingIce()){
                canJump = true;
            }
        }
        if(head() && canHead){
            vY = 0;
            canHead = false;
        }
        if(!ground() && !isDashing && !cheatsOn){
            vY += g;
        }
    }

    /**
     * Sets movement restrictions on the player depending on the current level
     */
    public void setLevelRestrictions(){
        if(touchingIce()){
            speed = 7;
        }else if(getWorld() == levels[2]){
            speed = 4;
        }else if(getWorld() == levels[5]){
            speed = 3;
        }else{
            speed = 5;
        }
        if(getWorld() == levels[3]){
            canJump = false;
        }
        if(getWorld() == levels[4]){
            g = 1.1;
        }else{
            g = 1;
        }
    }

    /** 
     * Teleports the player back to the last touched checkpoint, teleports to start of the world if none touched.
     */
    public void warpToCheckpoint(){
        if(platformDY >= 1912){
            getWorld().moveCam(-movedX, -movedY);
            
            // Resetting location tracking on the player
            platformDY = 0;
            vY = 0;
            vX = 0;
            saveX -= movedX;
            saveY -= movedY;
            movedX = 0;
            movedY = 0;
            
            warpedToCheckpoint = true;
            if(((Levels)getWorld()).breakingBlock.length != 0){
                ((Levels)getWorld()).resetBreakingBlocks();
            }
            checkStuckY();
            Stats.deaths++;
        }
    }

    /** 
     * Sets the direction of the sprites to be used
     */
    public void setDir(){
        if(Greenfoot.isKeyDown(left)){
            dir = -1;
        }
        if(Greenfoot.isKeyDown(right)){
            dir = 1;
        }
    }

    /** 
     * Controls player movement based on user input
     */
    public void movement(){
        if(Greenfoot.isKeyDown(left) && !left() && !isDashing){
            vX = -speed;
        }
        if(Greenfoot.isKeyDown(right) && !right() && !isDashing){
            vX = speed;
        }
        if(Greenfoot.isKeyDown(jump)&& (ground() || cheatsOn)){
            vY = jumpVelocity;
            jumped = true;
            canHead = true;
            markFrame = frames;
            isDashing = false;
            if(touchingIce()){
                canJump = false;
            }
            Stats.jumps++;
        }
        if(!ground() && Greenfoot.isKeyDown(jump) && canJump && (frames - markFrame) >= 9){
            vY = jumpVelocity;
            canJump = false;
            jumped = true;
            canHead = true;
            isDashing = false;
            Stats.jumps++;
        }
        if(Greenfoot.isKeyDown(dash) && (frames - isDashingCDTracker) > 35){
            vX = (speed*4+2) * dir;
            isDashing = true;
            isDashingCDTracker = frames;
        }
    }

    /** 
     * Animates the player using the appropriate sprites from the sprite arrays based on the direction that the player
     * is facing
     */
    public void animate(){
        // Jump animation
        if(jumped && canJump){
            if(dir == -1){
                GreenfootImage mirror = new GreenfootImage("jump3.png");
                mirror.mirrorHorizontally();
                setImage(mirror);
            }else{
                setImage(jumpSprite);
            }
        }else if(!canJump){
            int jumpFrame = frames%12/3;
            if(dir == 1){
                setImage(jumpS[jumpFrame]);
            }else{
                setImage(jumpSL[jumpFrame]);
            }
            jumped = false;
        }
        // Fall animation
        if(vY >= 0){
            int fallFrame = frames%8/4;
            if(dir == -1){
                setImage(fallL[fallFrame]);
            }else{
                setImage(fall[fallFrame]);
            }
        }
        // Idle animation
        if(ground() && vX == 0){
            int idleFrame = frames % 24/6;
            if(dir == -1){
                setImage(idleL[idleFrame]);
            }else{
                setImage(idle[idleFrame]);
            }
        }
        // Run animation
        if(ground() && (vX != 0 || Greenfoot.isKeyDown(left) || Greenfoot.isKeyDown(right))){
            int runFrame = frames%24/4;
            if(dir == -1){
                setImage(runL[runFrame]);
            }else{
                setImage(run[runFrame]);
            }
        }
        //Dash animation
        if(isDashing){
            if(dir == -1){
                GreenfootImage leftisDashing = new GreenfootImage("dash.png");
                leftisDashing.mirrorHorizontally();
                setImage(leftisDashing);
            }else{
                setImage(new GreenfootImage("dash.png"));
            }
        }
    }

    /** All of the below use the idle sprite's dimensions for accuracy reasons (the sprite is 38x58) */
    /** 
     * Checks whether the player is overlapping with a block in the vertical direction (top/bottom)
     * If they are, shifts the player up/down appropriately so that they are no longer in the block
     */
    public void checkStuckY(){
        // Checking for overlap at the bottom of the player
        if(checkStuckB() && !checkStuckT() && (vY != 0 || warpedToCheckpoint)){
            Block temp = null;
            if(dir == 1){
                temp = (Block) getOneObjectAtOffset(38/2-3, 58/2, Block.class);
                if(temp == null){
                    temp = (Block) getOneObjectAtOffset(-38/2+5, 58/2, Block.class);
                }
            }else{
                temp = (Block) getOneObjectAtOffset(38/2-5, 58/2, Block.class);
                if(temp == null){
                    temp = (Block) getOneObjectAtOffset(-38/2+3, 58/2, Block.class);
                }
            }
            if(temp != null){
                int blockY = temp.getY();
                int shiftY = temp.getImage().getHeight()/2+58/2-(blockY-getY());
                getWorld().moveCam(0, -shiftY);
                movedY += -shiftY;
                if(!help){
                    saveY += -shiftY;
                }
                vY = 0;
            }
            warpedToCheckpoint = false;
        }
        // Checking for overlap at the top of the player
        if(checkStuckT() && !checkStuckB() && (vY != 0 || warpedToCheckpoint)){
            Block temp1 = null;
            if(dir == 1){
                temp1 = (Block) getOneObjectAtOffset(38/2-3, -58/2, Block.class);
                if(temp1 == null){
                    temp1 = (Block) getOneObjectAtOffset(-38/2+5, -58/2, Block.class);
                }
            }else{
                temp1 = (Block) getOneObjectAtOffset(38/2-5, -58/2, Block.class);
                if(temp1 == null){
                    temp1 = (Block) getOneObjectAtOffset(-38/2+3, -58/2, Block.class);
                }
            }
            if(temp1 != null){
                int blockY1 = temp1.getY();
                int shiftY1 = temp1.getImage().getHeight()/2+58/2-Math.abs(blockY1-getY());
                getWorld().moveCam(0, shiftY1);
                movedY += shiftY1;
                saveY += shiftY1;
                vY = 0;
            }
            warpedToCheckpoint = false;
        }
    }

    /** 
     * Checks whether the player overlaps with a block in the x direction (left/right)
     * If so, the player is shifted left/right appropriately so that they are no longer in the block
     */
    public void checkStuckX(){
        // Checking for overlap at the left side of the player
        if(checkStuckL() && !checkStuckR()){
            Block temp = (Block) getOneObjectAtOffset(-38/2+3, 58/2-1, Block.class);
            if(temp == null){
                temp = (Block) getOneObjectAtOffset(-38/2+3, -58/2+1, Block.class);
            }
            if(temp == null){
                temp = (Block) getOneObjectAtOffset(-38/2+3,0,Block.class);
            }
            if(temp != null){
                int blockX = temp.getX();
                int shiftX = temp.getImage().getWidth()/2+38/2-Math.abs(blockX-getX());
                getWorld().moveCam(shiftX,0);
                movedX += shiftX;
                saveX += shiftX;
            }
        }
        // Checking for overlap at the right side of the player
        if(checkStuckR() && !checkStuckL()){
            Block temp1 = (Block) getOneObjectAtOffset(38/2-3, 58/2-1, Block.class);
            if(temp1 == null){
                temp1 = (Block) getOneObjectAtOffset(38/2-3, -58/2+1, Block.class);
            }
            if(temp1 == null){
                temp1 = (Block) getOneObjectAtOffset(38/2-3,0,Block.class);
            }
            if(temp1 != null){
                int blockX1 = temp1.getX();
                int shiftX1 = temp1.getImage().getWidth()/2+38/2-(blockX1-getX());
                getWorld().moveCam(-shiftX1,0);
                movedX -= shiftX1;
                saveX -= shiftX1;
            }
        }
    }

    /**
     * Checks whether or not the player's bottom overlaps with a block
     * 
     * @return True if the player's bottom overlaps with a block, false otherwise
     */
    public boolean checkStuckB(){
        if(vY < 0){
            return false;
        }
        if(dir == 1){
            return (getOneObjectAtOffset(-38/2+6, 58/2-1, Block.class) != null|| 
                getOneObjectAtOffset(38/2-2, 58/2-1, Block.class) != null);
        }else{
            return (getOneObjectAtOffset(-38/2+2, 58/2-1, Block.class) != null || 
                getOneObjectAtOffset(38/2-6, 58/2-1, Block.class) != null);
        }
    }

    /**
     * Checks whether or not the player's top overlaps with a block
     * 
     * @return True if the player's top overlaps with a block, false otherwise
     */
    public boolean checkStuckT(){
        if(vY >= 0){
            return false;
        }
        return (((getOneObjectAtOffset(-38/2+6, -58/2+1, Block.class) != null) && 
                (getOneObjectAtOffset(-38/2+2, -58/2+1, Block.class) != null)) || 
            ((getOneObjectAtOffset(38/2-2, -58/2+1, Block.class) != null) && 
                (getOneObjectAtOffset(38/2-6, -58/2+1, Block.class) != null)));
    }

    /**
     * Checks whether or not the player's left overlaps with a block
     * 
     * @return True if the player's left overlaps with a block, false otherwise
     */
    public boolean checkStuckL(){
        if(dir == 1){
            return (false);
        }else{
            return (((getOneObjectAtOffset(-38/2+3, -58/2, Block.class) != null) && 
                    (getOneObjectAtOffset(-38/2+3, -58/2+1, Block.class) != null)) || 
                ((getOneObjectAtOffset(-38/2+3, 58/2, Block.class) != null) && 
                    (getOneObjectAtOffset(-38/2+3, 58/2-1, Block.class) != null)) || 
                (getOneObjectAtOffset(-38/2+3,0,Block.class) != null));
        }
    }

    /**
     * Checks whether or not the player's right overlaps with a block
     * 
     * @return True if the player's right overlaps with a block, false otherwise
     */
    public boolean checkStuckR(){
        if(dir == 1){
            return (((getOneObjectAtOffset(38/2-2, -58/2, Block.class) != null) && 
                    (getOneObjectAtOffset(38/2-2, -58/2+1, Block.class) != null)) || 
                ((getOneObjectAtOffset(38/2-2, 58/2-1, Block.class) != null) && 
                    (getOneObjectAtOffset(38/2-2, 58/2, Block.class) != null)) ||
                (getOneObjectAtOffset(38/2-2, 0, Block.class) != null));
        }else{
            return (false);
        }
    }

    /**
     * Checks whether or not the player's bottom touches a block
     * 
     * @return True if the player's bottom touches a block, false otherwise
     */
    public boolean ground(){
        if(dir == 1){
            return ((getOneObjectAtOffset(-38/2+5, 58/2, Block.class) != null) ||
                (getOneObjectAtOffset(38/2-3, 58/2, Block.class) != null));
        }else{
            return ((getOneObjectAtOffset(-38/2+3, 58/2, Block.class) != null) ||
                (getOneObjectAtOffset(38/2-5, 58/2, Block.class) != null));
        }
    }

    /**
     * Checks whether or not the player's top touches a block
     * 
     * @return True if the player's top touches a block, false otherwise
     */
    public boolean head(){
        if(dir == 1){
            return (getOneObjectAtOffset(-38/2+3, -58/2+1, Block.class) != null || 
                (getOneObjectAtOffset(38/2-3, -58/2+1, Block.class) != null));
        }else{
            return (getOneObjectAtOffset(-38/2+3, -58/2+1, Block.class) != null || 
                (getOneObjectAtOffset(38/2-3, -58/2+1, Block.class) != null));
        }
    }

    /**
     * Checks whether or not the player's left touches a block
     * 
     * @return True if the player's left touches a block, false otherwise
     */
    public boolean left(){
        return (((getOneObjectAtOffset(-38/2+1, -58/2, Block.class) != null) && 
                (getOneObjectAtOffset(-38/2+1, -58/2+1, Block.class) != null)) || 
            ((getOneObjectAtOffset(-38/2+1, 58/2-1, Block.class) != null) && 
                (getOneObjectAtOffset(-38/2+1, 58/2, Block.class) != null)) ||
            (getOneObjectAtOffset(-38/2+1,0,Block.class) != null));
    }

    /**
     * Checks whether or not the player's right touches a block
     * 
     * @return True if the player's right touches a block, false otherwise
     */
    public boolean right(){
        return (((getOneObjectAtOffset(38/2, -58/2, Block.class) != null) && 
                (getOneObjectAtOffset(38/2, -58/2+1, Block.class) != null)) || 
            ((getOneObjectAtOffset(38/2, 58/2-1, Block.class) != null) && 
                (getOneObjectAtOffset(38/2, 58/2, Block.class) != null)) ||
            (getOneObjectAtOffset(38/2,0,Block.class) != null));
    }

    /**
     * Bounces the player up if they touch a slime block by 90% of the velocity before touching it.
     */
    public void bounce(){
        if((getOneObjectAtOffset(-38/2+5, 58/2, SlimeBlock.class) != null) ||
        (getOneObjectAtOffset(38/2-3, 58/2, SlimeBlock.class) != null) || 
        (getOneObjectAtOffset(-38/2+3, 58/2, SlimeBlock.class) != null) ||
        (getOneObjectAtOffset(38/2-5, 58/2, SlimeBlock.class) != null)){
            bounceVY = bounceVY*-9/10;
            getWorld().moveCam(0,bounceVY);
            movedY += bounceVY;
            saveY += bounceVY;
            canJump = true;
            jumped = false;
            getWorld().repaint();
            vY = bounceVY;
            platformDY = 0;
        }
    }

    /**
     * Checks if the player is touching an IceBlock.
     * 
     * @return True if the player is touching an IceBlock, false otherwise.
     */
    public boolean touchingIce(){
        return((getOneObjectAtOffset(-38/2+5, 58/2, IceBlock.class) != null) ||
            (getOneObjectAtOffset(38/2-3, 58/2, IceBlock.class) != null) || 
            (getOneObjectAtOffset(-38/2+3, 58/2, IceBlock.class) != null) ||
            (getOneObjectAtOffset(38/2-5, 58/2, IceBlock.class) != null));
    }

    /**
     * Sets the world to the appropriate level.
     */
    public void setWorld(int level){
        getWorld().changeWorld(levels[level]);
    }
}