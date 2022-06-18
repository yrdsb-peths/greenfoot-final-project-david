import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A world that describes the lore of the game
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Story extends ScrollWorld
{
    private int frames = 0;
    /**
     * Constructs a help world
     */
    public Story()
    {
        super(711,400,1,true);
        GreenfootImage backButton = new GreenfootImage("orangeButton.png");
        backButton.setFont(DetailsRenderer.constantiaB40);
        backButton.drawString("BACK",45,30);
        backButton.scale(100,18);
        BackButton back = new BackButton(new StartingScreen(),backButton);
        addCameraFollower(back,70-getWidth()/2,22-getHeight()/2);
        Label description = new Label("You are a traveller setting off on a\n" + 
                                      "Journey. You will journey through many\n" + 
                                      "biomes and endure many hardships.\n" + 
                                      "Enjoy you journey, traveller!",35);
        addObject(description,getWidth()/2,getHeight()/2);
    }

    public void act(){
        if(frames%2 == 0){
            moveCam(1,0);
        }
        frames++;
    }
}
