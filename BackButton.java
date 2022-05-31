import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BackButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BackButton extends Actor
{
    /**
     * Act - do whatever the BackButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    World prevWorld;
    public BackButton(World prevWorld, GreenfootImage bg){
        this.prevWorld = prevWorld;
        setImage(bg);
    }
    
    public void act()
    {
        if(Greenfoot.mouseClicked(this)){
            ScrollWorld temp = (ScrollWorld)getWorld();
            temp.changeWorld(prevWorld);
        }
    }
}
