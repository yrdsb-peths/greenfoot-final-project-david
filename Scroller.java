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
    /**
     * Act - do whatever the Scroller wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Scroller(){
        frames = 0;
        setImage(new GreenfootImage(10,10));
    }
    public void act()
    {
        if(frames%2 == 0){
            getWorld().moveCam(1,0);
        }
        frames++;
    }
}
