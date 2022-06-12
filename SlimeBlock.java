import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SlimeBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SlimeBlock extends Block
{
    /**
     * Act - do whatever the SlimeBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public SlimeBlock(){
        super(new GreenfootImage("slime.png"));
        GreenfootImage slime = new GreenfootImage("slime.png");
        slime.scale(48,48);
        setImage(slime);
    }
}
