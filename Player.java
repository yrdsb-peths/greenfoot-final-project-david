import greenfoot.*;
import java.util.*;

/**
 * The Player's Class
 * 
 */
public class Player extends ScrollActor
{
    // Movement keybinds
    public static String jump = "up";
    public static String left = "left";
    public static String right = "right";
    public static String dash = "0";
    // Movement speeds in the x and y directions. Positive is to the right (for x) or down (for y)
    private int vY = 0;
    private int vX = 0;
    public static int speed = 5;
    private boolean isDashing = false;
    private int bounceVY = 0;
    // Gravity: how many cells per frame the player's vertical velocity (vY) increases by every frame
    public static double g = 1;
    // The displacement of the player relative to a location
    public int movedX = 0;
    public int movedY = 0;
    public static int saveX = 0;
    public static int saveY = 0;
    // Used to determine whether or not the player can jump/double jump; also aids in setting the jump/double jump animations
    public static boolean canJump = true;
    private boolean jumped = false;
    private int platformDY = 0;
    private int markFrame = 0;
    private boolean canHead = true;
    private int isDashingCDTracker = 0;
    // Direction that the player is facing and frame count (number of times act() is run). Both are used for sprites
    private int dir = 1;
    private int frames = 0;
    // Arrays (and an image) for sprites of various actions
    private GreenfootImage[] run = new GreenfootImage[6];
    private GreenfootImage[] runL = new GreenfootImage[6];
    private GreenfootImage[] idle = new GreenfootImage[4];
    private GreenfootImage[] idleL = new GreenfootImage[4];
    private GreenfootImage[] fall = new GreenfootImage[2];
    private GreenfootImage[] fallL = new GreenfootImage[2];
    private GreenfootImage[] jumpS = new GreenfootImage[4];
    private GreenfootImage[] jumpSL = new GreenfootImage[4];
    private GreenfootImage jumpSprite = new GreenfootImage("jump3.png");

    public static boolean warpedToCheckpoint = false;

    public static boolean paused = false;
    public static int level = 0;
    public static boolean help = false;
    public static Levels[] levels = {new Level1(),new Level2(), new Level3(),new Level4(), new Level5()};

    /**
     * Constructs a player object and sets up the arrays of sprites with proper images
     */
    public Player(){
        super();
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

    protected void addedToWorld(World world)  
    {
        ScrollWorld temp = (ScrollWorld)world;
        temp.moveCam(saveX,saveY);
        movedX += saveX;
        movedY += saveY;
    }

    public void act()
    {
        setGravity();
        if(!paused){
            if(touchingIce()){
                speed = 7;
            }else if(getWorld() == levels[2]){
                speed = 4;
            }else{
                speed = 5;
            }
            if(getWorld() == levels[3]){
                canJump = false;
                isDashingCDTracker = frames;
            }
            if(ground()){
                vY = 0;
                platformDY = 0;
                canJump = true;
                // movedX and movedY here are only for testing; remove these to use checkpoints effectively
                // movedX = 0;
                // movedY = 0;
            }
            if(head() && canHead){
                vY = 0;
                canHead = false;
            }
            if(!ground() && !isDashing){
                vY += g;
            }
            if(isDashing && vX != 0){
                vX -= dir*2;
                vY = 0;
            }else if(vX == 0){
                isDashing = false;
            }

            movement();
            setDir();
            getWorld().moveCam(vX,0);
            checkStuckX();
            getWorld().moveCam(0,vY);
            bounceVY = vY;
            movedY += vY;
            platformDY += Math.abs(vY);
            checkStuckY();
            bounce();
            animate();

            movedX += vX;
            if(!help){
                saveX += vX;
                saveY += vY;
            }
            if(!isDashing && !touchingIce()){
                vX = 0;
            }
            if(touchingIce() && vX != 0 && !(Greenfoot.isKeyDown(left) || Greenfoot.isKeyDown(right)) && frames%4 == 0 && !isDashing){
                vX -= dir;
            }
            frames++;

            warpToCheckpoint();
            setCheckpointLocation();
        }
    }
    
    public void setGravity(){
        if(getWorld() == levels[4]){
            g = 1.1;
        }else{
            g = 1;
        }
    }

    /** Sets the location to teleport back to */
    public void setCheckpointLocation(){
        if(getOneIntersectingObject(Checkpoint.class) != null){
            Checkpoint checkpoint = (Checkpoint) getOneIntersectingObject(Checkpoint.class);
            movedX = getX() - checkpoint.getX();
            movedY = 0;
        }
    }

    /** Teleports the player back to the location set by SetCheckpointLocation(), default is spawn location */
    public void warpToCheckpoint(){
        if(platformDY >= 44*48-200){
            getWorld().moveCam(-movedX, -movedY);
            platformDY = 0;
            vY = 0;
            vX = 0;
            saveX -= movedX;
            saveY -= movedY;
            movedX = 0;
            movedY = 0;
            warpedToCheckpoint = true;
            checkStuckY();
        }
    }

    /** Sets the direction of the sprites to be used */
    public void setDir(){
        if(vX < 0){
            dir = -1;
        }else if(vX > 0){
            dir = 1;
        }
    }

    /** To control player movement */
    public void movement(){
        if(Greenfoot.isKeyDown(left) && !left() && !isDashing){
            vX = -speed;
        }
        if(Greenfoot.isKeyDown(right) && !right() && !isDashing){
            vX = speed;
        }
        if(Greenfoot.isKeyDown(jump)&& ground()){
            vY = -12;
            jumped = true;
            canHead = true;
            markFrame = frames;
            isDashing = false;
        }
        if(!ground() && Greenfoot.isKeyDown(jump) && canJump && (frames - markFrame) >= 8){
            vY = -12;
            canJump = false;
            jumped = true;
            canHead = true;
            isDashing = false;
        }
        if(Greenfoot.isKeyDown(dash) && (frames - isDashingCDTracker) > 35){
            vX = (speed*4+2) * dir;
            isDashing = true;
            isDashingCDTracker = frames;
        }
    }

    /** To animate the player using the appropriate sprites from the arrays set up in the constructor */
    public void animate(){
        if(jumped && canJump){
            if(dir == -1){
                GreenfootImage mirror = new GreenfootImage("jump3.png");
                mirror.mirrorHorizontally();
                setImage(mirror);
            }else if(dir == 1){
                setImage(jumpSprite);
            }
        }else if(!canJump){
            int jumpFrame = frames%12/3;
            if(dir == 1){
                setImage(jumpS[jumpFrame]);
            }else if(dir == -1){
                setImage(jumpSL[jumpFrame]);
            }
            jumped = false;
        }
        if(vY >= 0){
            int fallFrame = frames%8/4;
            if(dir == -1){
                setImage(fallL[fallFrame]);
            }else{
                setImage(fall[fallFrame]);
            }
        }
        if(ground() && vX == 0){
            int idleFrame = frames % 24/6;
            if(dir == -1){
                setImage(idleL[idleFrame]);
            }else{
                setImage(idle[idleFrame]);
            }
        }
        if(ground() && (vX != 0 || Greenfoot.isKeyDown(left) || Greenfoot.isKeyDown(right))){
            int runFrame = frames%24/4;
            if(dir == -1){
                setImage(runL[runFrame]);
            }else{
                setImage(run[runFrame]);
            }
        }
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
    /** Checks whether the player is overlapping with a block in the vertical direction (top/bottom)
     *  If they are, shifts the player up/down appropriately so that they are no longer in the block
     */
    public void checkStuckY(){
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

    /** Checks whether the player overlaps with a block in the x direction (left/right)
     *  If so, the player is shifted left/right appropriately so that they are no longer in the block
     */
    public void checkStuckX(){
        if(checkStuckL() && !checkStuckR()){
            Block temp = (Block) getOneObjectAtOffset(-38/2+3, 58/2-1, Block.class);
            if(temp == null){
                temp = (Block) getOneObjectAtOffset(-38/2+3, -58/2+1, Block.class);
            }
            if(temp != null){
                int blockX = temp.getX();
                int shiftX = temp.getImage().getWidth()/2+38/2-Math.abs(blockX-getX());
                getWorld().moveCam(shiftX,0);
                movedX += shiftX;
                saveX += shiftX;
            }
        }
        if(checkStuckR() && !checkStuckL()){
            Block temp1 = (Block) getOneObjectAtOffset(38/2-3, 58/2-1, Block.class);
            if(temp1 == null){
                temp1 = (Block) getOneObjectAtOffset(38/2-3, -58/2+1, Block.class);
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

    /** Checks whether or not the bottom of the player overlaps with a block */
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

    /** Checks whether or not the top of the player overlaps with a block */
    public boolean checkStuckT(){
        if(vY >= 0){
            return false;
        }
        return (((getOneObjectAtOffset(-38/2+6, -58/2+1, Block.class) != null) && 
                (getOneObjectAtOffset(-38/2+2, -58/2+1, Block.class) != null)) || 
            ((getOneObjectAtOffset(38/2-2, -58/2+1, Block.class) != null) && 
                (getOneObjectAtOffset(38/2-6, -58/2+1, Block.class) != null)));
    }

    /** Checks whether or not the left of the player overlaps with a block */
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

    /** Checks whether or not the right of the player overlaps with a block */
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

    /** Checks whether or not the player is standing on a block (includes overlap) */
    public boolean ground(){
        if(dir == 1){
            return ((getOneObjectAtOffset(-38/2+5, 58/2, Block.class) != null) ||
                (getOneObjectAtOffset(38/2-3, 58/2, Block.class) != null));
        }else{
            return ((getOneObjectAtOffset(-38/2+3, 58/2, Block.class) != null) ||
                (getOneObjectAtOffset(38/2-5, 58/2, Block.class) != null));
        }
    }

    /** Checks whether or not the player's head is hitting a block (includes overlap) */
    public boolean head(){
        if(dir == 1){
            return (getOneObjectAtOffset(-38/2+3, -58/2+1, Block.class) != null || 
                (getOneObjectAtOffset(38/2-3, -58/2+1, Block.class) != null));
        }else{
            return (getOneObjectAtOffset(-38/2+3, -58/2+1, Block.class) != null || 
                (getOneObjectAtOffset(38/2-3, -58/2+1, Block.class) != null));
        }
    }

    /** Checks whether or not the player's left is touching a block (includes overlap) */
    public boolean left(){
        return (((getOneObjectAtOffset(-38/2+1, -58/2, Block.class) != null) && 
                (getOneObjectAtOffset(-38/2+1, -58/2+1, Block.class) != null)) || 
            ((getOneObjectAtOffset(-38/2+1, 58/2-1, Block.class) != null) && 
                (getOneObjectAtOffset(-38/2+1, 58/2, Block.class) != null)) ||
            (getOneObjectAtOffset(-38/2+1,0,Block.class) != null));
    }

    /** Checks whether or not the player's right is touching a block (includes overlap) */
    public boolean right(){
        return (((getOneObjectAtOffset(38/2, -58/2, Block.class) != null) && 
                (getOneObjectAtOffset(38/2, -58/2+1, Block.class) != null)) || 
            ((getOneObjectAtOffset(38/2, 58/2-1, Block.class) != null) && 
                (getOneObjectAtOffset(38/2, 58/2, Block.class) != null)) ||
            (getOneObjectAtOffset(38/2,0,Block.class) != null));
    }
    
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
    
    public boolean touchingIce(){
        return((getOneObjectAtOffset(-38/2+5, 58/2, IceBlock.class) != null) ||
            (getOneObjectAtOffset(38/2-3, 58/2, IceBlock.class) != null) || 
            (getOneObjectAtOffset(-38/2+3, 58/2, IceBlock.class) != null) ||
            (getOneObjectAtOffset(38/2-5, 58/2, IceBlock.class) != null));
    }
    
    public Checkpoint getCheckpoint(){
        return (Checkpoint)getOneIntersectingObject(Checkpoint.class);
    }
}