import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Help here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story extends ScrollWorld
{
    Label description;
    Font constantia = new Font("Constantia",true,false,40);
    /**
     * Constructor for objects of class Help.
     * 
     */
    public Story()
    {
        super(711,400,1,true);
        
        GreenfootImage backButton = new GreenfootImage("orangeButton.png");
        backButton.setFont(constantia);
        backButton.drawString("BACK",45,30);
        backButton.scale(100,18);
        BackButton back = new BackButton(new StartingScreen(),backButton);
        addCameraFollower(back,70-getWidth()/2,22-getHeight()/2);
        
        addCameraFollower(new Scroller(),getWidth()/2,getHeight()/2);
        description = new Label("You are a traveller setting off on a\n" + 
                                "Journey. You will journey through many\n" + 
                                "biomes and endure many hardships.\n" + 
                                "Enjoy you journey, traveller!",35);
        addObject(description,getWidth()/2,getHeight()/2);
    }
}
