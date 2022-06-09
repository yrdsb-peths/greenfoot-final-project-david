import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gate extends ScrollActor
{
    /**
     * Act - do whatever the Gate wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int enterCount = 0;
    
    public Gate(GreenfootImage image){
        setImage(image);
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("enter") && getOneIntersectingObject(Player.class) != null && enterCount < 1){
            Player.level++;
            Player.saveX = 0;
            Player.saveY = 0;
            Levels nextLvl = Player.levels[Player.level];
            getWorld().changeWorld(nextLvl);
            enterCount++;
        }
    }
}
