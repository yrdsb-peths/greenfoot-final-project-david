import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerDummy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerDummy extends Actor
{
    private GreenfootImage[] run = new GreenfootImage[6];
    private int frames;
    /**
     * Act - do whatever the PlayerDummy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public PlayerDummy(){
        for(int i = 0; i < 6; i++){
            run[i] = new GreenfootImage("run" + (i + 1) + ".png");
        }
    }
    
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
