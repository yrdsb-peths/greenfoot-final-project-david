import greenfoot.*;

/**
 * Write a description of class TransitionAssist here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TransitionAssist extends Actor
{
    Color color;
    private int frames = 0;
    private World world;
    GreenfootImage fade = new GreenfootImage(711,400);
    private boolean fadeIn;
    // world to set to
    /**
     * Constructor for objects of class TransitionAssist
     */
    public TransitionAssist(World world)
    {
        color = new Color(0,0,0,0);
        fade.setColor(color);
        fade.fill();
        setImage(fade);
        this.world = world;
    }

    public void act(){
        if(frames < 28){
            color = new Color(0,0,0,frames*8);
            fade.setColor(color);
            fade.fill();
            setImage(fade);
        }
        if(frames >= 28){
            Player.paused = false;
            getWorld().removeObject(this);
            Greenfoot.setWorld(world);
        }
        frames++;
    }
}
