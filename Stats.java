import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stats here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stats extends Actor
{
    /**
     * Act - do whatever the Stats wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static int jumps = 0;
    public static int deaths = 0;
    public static long startTime;
    public static long endTime;
    private boolean start;
    public Stats(boolean start){
        start = start;
        setImage(new GreenfootImage(1,1));
    }
    
    public static void startTimer(){
        startTime = System.currentTimeMillis();
    }
    
    public static void endTimer(){
        endTime = System.currentTimeMillis();
    }
}
