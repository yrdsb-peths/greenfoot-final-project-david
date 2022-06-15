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
    private int volume = ScrollWorld.volume;
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
            volume = volume - volume/28;
            BGMManager.setVolume(volume);
        }
        if(frames >= 28){
            Player.paused = false;
            BGMManager.stopAllMusic();
            BGMManager.setMusic(checkWorld());
            BGMManager.setVolume(ScrollWorld.volume);
            getWorld().removeObject(this);
            if(Player.level == 0){
                Stats.startTimer();
            }
            Greenfoot.setWorld(world);
        }
        frames++;
    }
    
    public int checkWorld(){
        if(world.getClass() == StartingScreen.class){
            return 0;
        }else if(world.getClass() == Story.class){
            return 1;
        }else if(world.getClass() == Settings.class){
            return 2;
        }else if(world.getClass() == Help.class){
            return 3;
        }else if(world.getClass() == Level1.class){
            return 4;
        }else if(world.getClass() == Level2.class){
            return 5;
        }else if(world.getClass() == Level3.class){
            return 6;
        }else if(world.getClass() == Level4.class){
            return 7;
        }else if(world.getClass() == Level5.class){
            return 8;
        }else if(world.getClass() == Level6.class){
            return 9;
        }else{
            return 10;
        }
    }
}
