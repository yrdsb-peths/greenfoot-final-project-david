import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A block that can move.
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class MovingBlock extends IceBlock
{
    public int maxDist;
    public Decor tracker;
    public int dir = 1;
    public int speed;
    public boolean moveX;

    /**
     * Instantiates a MovingBlock
     * 
     * @param image The image of the block
     * @param maxDist The maximum distance that the block can move in the given direction from the point  
     * that it is added to (positive x or positive y)
     * @param tracker An object to track the original location of the movingBlock
     * @param speed How fast the block moves
     * @param moveX Whether or not the block moves in the x direction. If false, the block moves in the y direction
     */
    public MovingBlock(GreenfootImage image,int maxDist,Decor tracker,int speed,boolean moveX){
        super(image);
        this.maxDist = maxDist;
        this.tracker = tracker;
        this.speed = speed;
        this.moveX = moveX;
    }

    public void act(){
        if(moveX){
            if(tracker.getX() == getX()){
                dir = 1;
            }else if(getX() == tracker.getX() + maxDist){
                dir = -1;
            }
        }else{
            if(tracker.getY() == getY()){
                dir = 1;
            }else if(getY() == tracker.getY() - maxDist){
                dir = -1;
            }
        }
        move(dir*speed);
    }
}
