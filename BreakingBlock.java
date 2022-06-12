import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FallingBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BreakingBlock extends Block
{
    private GreenfootImage[] sprites = new GreenfootImage[10];
    public int frames = 0;
    public int spriteCount = 0;
    public GreenfootImage image;
    private int frequency;
    public boolean removed = false;
    public int x;
    public int y;
    /**
     * Act - do whatever the FallingBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public BreakingBlock(GreenfootImage image, int frequency){
        super(image);
        this.image = image;
        this.frequency = frequency;
        for(int i = 0; i < 10; i++){
            sprites[i] = new GreenfootImage("destroy_stage_" + i + ".png");
            sprites[i].scale(48,48);
        }
    }

    public void act()
    {
        // Add your action code here.
        frames++;
        if(getOneIntersectingObject(Player.class) != null && getOneIntersectingObject(Player.class).getY() < (getY()-24)){
            if(spriteCount == 10*frequency){
                removed = true;
                getWorld().removeObject(this);
            }
            GreenfootImage broken = new GreenfootImage(image);
            broken.drawImage(sprites[spriteCount%(10*frequency)/frequency],0,0);
            setImage(broken);
            spriteCount++;
        }
    }
    
    public void reset(){
        setImage(image);
        frames = 0;
        spriteCount = 0;
    }
}
