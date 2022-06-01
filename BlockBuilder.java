import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlockBuilder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlockBuilder extends ScrollActor
{
    /**
     * Act - do whatever the BlockBuilder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int x, y = -100;
    private int camX, camY;
    private GreenfootImage blank = new GreenfootImage(1,1);
    int button = 0;
    public void act()
    {
        setImage(blank);
        MouseInfo m = Greenfoot.getMouseInfo();
        if(m != null){
            x = m.getX();
            y = m.getY();
            button = m.getButton();
        }
        setLocation(x,y);
        if(button == 1){
            Block block = new Block(false,new GreenfootImage("magma block.png"));
            getWorld().addObject(block, m.getX(), m.getY());
        }
        if(button == 3){
            Block block = new Block(true,new GreenfootImage("magma block.png"));
            getWorld().addObject(block, m.getX(), m.getY());
        }
    }
}
