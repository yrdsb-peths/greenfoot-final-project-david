import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class made for the sole purpose of providing a way to manage the music
 * 
 * @author David Jiang 
 * @version 2022/06/15
 */
public class BGMManager
{
    public static int volume = 50;
    
    // An array storing all of the music tracks
    public static GreenfootSound[] music = {new GreenfootSound("Start.mp3"),new GreenfootSound("Story.mp3"),
                                            new GreenfootSound("Settings.mp3"),new GreenfootSound("Help.mp3"),
                                            new GreenfootSound("Level1.mp3"),new GreenfootSound("Level2.mp3"),
                                            new GreenfootSound("Level3.mp3"),new GreenfootSound("Level4.mp3"),
                                            new GreenfootSound("Level5.mp3"),new GreenfootSound("Level6.mp3"),
                                            new GreenfootSound("Ending.mp3")};
    /**
     * Sets the music based on the given world
     * 
     * @param world The world that the music is to be played for
     */
    public static void setMusic(World world){
        music[checkWorld(world)].playLoop();
        setVolume(volume);
    }
    
    /**
     * Stops any currently playing music
     */
    public static void stopAllMusic(){
        for(GreenfootSound sound:music){
            sound.stop();
        }
    }
    
    /**
     * Sets the volume
     * 
     * @param volume The volume to be set to, the value should be from 0 to 100, inclusive
     */
    public static void setVolume(int volume){
        for(GreenfootSound sound:music){
            sound.setVolume(volume);
        }
    }
    
    /**
     * Checks the index of the music list to be played, depending on the world
     * 
     * @param world The world that the music is to be played for
     * 
     * @return The corresponding index of the world's music in the music array
     */
    private static int checkWorld(World world){
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
