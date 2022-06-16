import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world to explain game mechanics and allow players to test movement
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Help extends ScrollWorld
{
    Label description;
    Block[] allBlocks = new Block[6];
    Label desc;
    /**
     * Initiates a new Help world
     * 
     */
    public Help()
    {
        super(711,400,1,true);

        Player demo = new Player();
        addCameraFollower(demo,213,50);

        for(int i = 0; i < 6; i++){
            Block demoGround = new Block(DetailsRenderer.grassBlock);
            addObject(demoGround, 425+i*48,315);
            allBlocks[i] = demoGround;
        }
        
        Label frame = new Label(new GreenfootImage("frame-1.png"));
        addObject(frame,355,200);
        
        GreenfootImage backButton = new GreenfootImage("orangeButton.png");
        backButton.setFont(DetailsRenderer.constantiaB40);
        backButton.drawString("BACK",45,30);
        backButton.scale(100,18);
        BackButton back = new BackButton(new StartingScreen(),backButton);
        addCameraFollower(back,70-getWidth()/2,22-getHeight()/2);
        
        desc = new Label("The default controls for movement are:\n" + 
                          "'up arrow' — jump\n" + 
                          "'left arrow' — move left\n" + 
                          "'right arrow' — move right\n" + 
                          "'0' — dash\n" + 
                          "It is also possible to jump in midair once\n" + 
                          "for each jump that starts on the ground\n" + 
                          "The controls can be changed in the settings,\n" + 
                          "which can be accessed in the home screen\n" + 
                          "Feel free to use the zone on the right to test\n" + 
                          "movement",27);
        addObject(desc,250,220);
        Player.help = true;
    }

    public void act(){
        // To scroll the small platform
        for(Block block : allBlocks){
            if(block.getX() < 415){
                block.setLocation(block.getX()+260,block.getY());
            }else if(block.getX() > 715){
                block.setLocation(block.getX()-260,block.getY());
            }
        }
    }
}
