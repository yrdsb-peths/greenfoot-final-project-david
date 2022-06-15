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
    private Font constantia = new Font("Constantia",30);
    private InGameText continueText = new InGameText("Press 'Enter' to continue.",Color.WHITE,constantia,false);
    private boolean end;

    public Gate(GreenfootImage image){
        setImage(image);
    }

    public Gate(GreenfootImage image,boolean end){
        setImage(image);
        this.end = end;
    }

    public void act()
    {
        if(getOneIntersectingObject(Player.class) != null){
            getWorld().addObject(continueText,720,330);
        }else{
            getWorld().removeObject(continueText);
        }
        if(Greenfoot.isKeyDown("enter") && getOneIntersectingObject(Player.class) != null && enterCount < 1 && !Player.paused){
            if(end){
                Stats.endTimer();
                getWorld().changeWorld(new EndScreen());
                enterCount++;
            }else{
                Player.level++;
                Player.saveX = 0;
                Player.saveY = 0;
                Levels nextLvl = Player.levels[Player.level];
                getWorld().changeWorld(nextLvl);
                enterCount++;
            }
        }
    }
}
