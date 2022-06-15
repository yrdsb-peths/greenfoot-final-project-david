import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Scroller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scroller extends ScrollActor
{
    private int frames;
    private int speed;
    /**
     * Act - do whatever the Scroller wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Scroller(){
        frames = 0;
        setImage(new GreenfootImage(10,10));
        speed = 1;
    }
    public Scroller(int speed){
        frames = 0;
        setImage(new GreenfootImage(10,10));
        this.speed = speed;
    }
    public void act()
    {
        if(frames%2 == 0){
            getWorld().moveCam(speed,0);
        }
        frames++;
    }
}
