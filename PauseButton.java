import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PauseButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PauseButton extends ScrollActor
{
    
    /**
     * Act - do whatever the PauseButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public PauseButton(){
        setup();
    }
    
    public void setup(){
        GreenfootImage pause = new GreenfootImage("orangeButton.png");
        pause.scale(20,20);
        GreenfootImage breaker = new GreenfootImage("pause.png");
        breaker.scale(20,20);
        pause.drawImage(breaker,0,0);
        setImage(pause);
    }
}
