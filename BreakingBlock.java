import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A block that breaks if the player stands on it for too long.
 * The block breaks after it cracks 10 times.
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class BreakingBlock extends Block
{
    public int spriteCount = 0;
    public GreenfootImage image;
    private int frequency;
    public boolean removed = false;
    public int x;
    public int y;
    /**
     * Instantiates a BreakingBlock.
     * 
     * @param image The image of the block
     * @param frequency How many frames between each time the block cracks
     */

    public BreakingBlock(GreenfootImage image, int frequency){
        super(image);
        this.image = image;
        this.frequency = frequency;
        for(int i = 0; i < 10; i++){
            DetailsRenderer.breaking[i] = new GreenfootImage("destroy_stage_" + i + ".png");
        }
    }
    
    public void act()
    {
        if(getOneIntersectingObject(Player.class) != null && getOneIntersectingObject(Player.class).getY() < (getY()-24)){
            if(spriteCount == 10*frequency){
                removed = true;
                getWorld().removeObject(this);
            }
            GreenfootImage broken = new GreenfootImage(image);
            broken.drawImage(DetailsRenderer.breaking[spriteCount%(10*frequency)/frequency],0,0);
            setImage(broken);
            spriteCount++;
        }
    }
    
    /**
     * Resets the state of the block.
     */
    public void reset(){
        setImage(image);
        spriteCount = 0;
    }
}
