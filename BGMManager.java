import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BGMManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BGMManager extends Actor
{
    /**
     * Act - do whatever the BGMManager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BGMManager(){
        setImage(new GreenfootImage(1,1));
    }
    
    public static void setMusic(int i){
        ScrollWorld.music[i].playLoop();
    }
    
    public static void stopAllMusic(){
        for(GreenfootSound sound:ScrollWorld.music){
            sound.stop();
        }
    }
    
    public static void setVolume(int volume){
        for(GreenfootSound sound:ScrollWorld.music){
            sound.setVolume(volume);
        }
    }
}
