import greenfoot.*;

/**
 * Creates a smooth, fading transition between worlds by slowly making a black panel fade in.
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class TransitionAssist extends Actor
{
    Color color;
    private int frames = 0;
    private World world;
    GreenfootImage fade = new GreenfootImage(711,400);
    private boolean fadeIn;
    private int volume = BGMManager.volume;

    /**
     * Constructor for objects of class TransitionAssist
     * 
     * @param world The world to set to at the end of the transition. 
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
            fadeOut();
        }
        if(frames >= 28){
            transition();
        }
        frames++;
    }
    
    /**
     * Makes the world fade out by making a black panel fade in.
     */
    public void fadeOut(){
        color = new Color(0,0,0,frames*8);
        fade.setColor(color);
        fade.fill();
        setImage(fade);
        volume = volume - volume/28;
        BGMManager.setVolume(volume);
    }

    /**
     * Changes the world. 
     */
    public void transition(){
        Player.paused = false;
        BGMManager.stopAllMusic();
        BGMManager.setMusic(world);
        BGMManager.setVolume(BGMManager.volume);
        getWorld().removeObject(this);
        if(Player.level == 0 && Player.saveY == 0){
            Stats.startTimer();
        }
        Greenfoot.setWorld(world);
    }
}
