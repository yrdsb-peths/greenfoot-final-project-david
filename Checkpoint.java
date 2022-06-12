import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CheckPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Checkpoint extends ScrollActor
{
    /**
     * Act - do whatever the CheckPoint wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Checkpoint(){
        GreenfootImage image = getImage();
        image.scale(48,58);
        setImage(image);
    }
}
