import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Block extends ScrollActor
{
    /**
     * Act - do whatever the Block wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean disappear;
    private int count = 0;
    private long time;
    private boolean setPos = true;
    public Block(boolean disappear,GreenfootImage image){
        this.disappear = disappear;
        setImage(image);
    }
    
    public void act()
    {
        time = System.currentTimeMillis();
        if(timer && disappear){
            if(count == 0){
                count = (int) time;
            }
            if(count == 1000){
                setImage("blocky.png");
            }
            if(count == 3000){
                setImage("blockyR.png");
            }
            if(count == 4500){
                getWorld().removeObject(this);
            }
            count++;
        }
    }
}
