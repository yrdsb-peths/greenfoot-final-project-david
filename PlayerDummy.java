import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An acting dummy of the player. It cannot be controlled by the user. 
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class PlayerDummy extends Actor
{
    private GreenfootImage[] run = new GreenfootImage[6];
    private int frames;
    
    /**
     * Initializes the dummy, sets up the animation sprites
     */
    public PlayerDummy(){
        for(int i = 0; i < 6; i++){
            run[i] = new GreenfootImage("run" + (i + 1) + ".png");
        }
    }
    
    /** Moves the dummy */
    public void act()
    {
        if(getX() < getWorld().getWidth()/2){
            move(3);
        }
        int runFrame = frames%30/5;
        setImage(run[runFrame]);
        frames++;
    }
}
