import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Help here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Help extends ScrollWorld
{
    Label description;
    Font constantia = new Font("Constantia",true,false,40);
    /**
     * Constructor for objects of class Help.
     * 
     */
    public Help()
    {
        super(711,400,1,true);
        
        GreenfootImage backButton = new GreenfootImage("orangeButton.png");
        backButton.setFont(constantia);
        backButton.drawString("BACK",45,30);
        backButton.scale(100,18);
        BackButton back = new BackButton(new StartingScreen(),backButton);
        addObject(back,70,22);
        
        addCameraFollower(new Scroller(),getWidth()/2,getHeight()/2);
        description = new Label("You are a traveller setting off on a journey.\n" + 
                                "You will journey through many biomes and endure\n" + 
                                "many hardships.\n" + 
                                "At the end of it all, you will see great wonders.\n" + 
                                "Enjoy you journey, traveller!",35);
        addObject(description,getWidth()/2,getHeight()/2);
    }
    
    public void act(){
        
    }
}
