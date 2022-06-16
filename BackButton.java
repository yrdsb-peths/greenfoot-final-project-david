import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A button that changes the world to another one
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class BackButton extends ScrollActor
{
    World prevWorld;
    /**
     * Instantiates a BackButton
     * 
     * @param prevWorld The world that the button will chagen to upon click
     * @param bg The image of the button
     */
    public BackButton(World prevWorld, GreenfootImage bg){
        this.prevWorld = prevWorld;
        setImage(bg);
    }
    
    /** Changes the world if clicked */
    public void act()
    {
        if(Greenfoot.mouseClicked(this)){
            ScrollWorld temp = (ScrollWorld)getWorld();
            temp.changeWorld(prevWorld);
        }
    }
}
