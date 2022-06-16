import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An object that can send the player to the next level.
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Gate extends ScrollActor
{
    private int enterCount = 0;
    private Font constantia = new Font("Constantia",30);
    private InGameText continueText = new InGameText("Press 'Enter' to continue.",Color.WHITE,constantia,false);
    private boolean end;

    /**
     * Instantiates a new Gate
     * 
     * @param image The image of the gate
     */
    public Gate(GreenfootImage image){
        setImage(image);
    }

    /**
     * Instantiates a new Gate
     * 
     * @param image The image of the gate
     * @param end Whether or not this is the last level
     */
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
                endGame();
            }else{
                nextLevel();
            }
        }
    }

    /**
     * Sends the player to the ending world.
     */
    private void endGame(){
        Stats.endTimer();
        getWorld().changeWorld(new EndScreen());
        enterCount++;
    }

    /**
     * Sends the player to the next level
     */
    private void nextLevel(){
        Player.level++;
        Player.saveX = 0;
        Player.saveY = 0;
        Levels nextLvl = Player.levels[Player.level];
        getWorld().changeWorld(nextLvl);
        enterCount++;
    }
}
