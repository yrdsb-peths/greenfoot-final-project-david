import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovingBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovingBlock extends IceBlock
{
    /**
     * Act - do whatever the MovingBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public int maxDist;
    public Decor tracker;
    public int dir = 1;
    public int speed;
    public boolean moveX;
    
    public MovingBlock(GreenfootImage image,int maxDist,Decor tracker,int speed,boolean moveX){
        super(image);
        this.maxDist = maxDist;
        this.tracker = tracker;
        this.speed = speed;
        this.moveX = moveX;
    }
}
