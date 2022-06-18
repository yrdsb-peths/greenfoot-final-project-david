import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Keeps track of all player stats. (Time, jumps, death)
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Stats extends Actor
{
    public static int jumps = 0;
    public static int deaths = 0;
    public static long startTime;
    public static long endTime;
    private boolean start;
    
    /**
     * Instantiates a Stats object
     * 
     * @param start Whether or not the timer starts (if true) or ends (if false). 
     */
    public Stats(boolean start){
        start = start;
        setImage(new GreenfootImage(1,1));
    }
    
    /**
     * Starts the timer.
     */
    public static void startTimer(){
        startTime = System.currentTimeMillis();
    }
    
    /**
     * Ends the timer.
     */
    public static void endTimer(){
        endTime = System.currentTimeMillis();
    }
}
