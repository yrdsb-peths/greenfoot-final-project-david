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
    Block[] allBlocks = new Block[4];
    Label desc;
    /**
     * Constructor for objects of class Help.
     * 
     */
    public Help()
    {
        super(711,400,1,true);

        Player demo = new Player();
        addCameraFollower(demo,213,50);

        GreenfootImage ground = new GreenfootImage("grass block.png");
        ground.scale(65,65);

        for(int i = 0; i < 4; i++){
            Block demoGround = new Block(false,ground);
            addObject(demoGround, 425+i*65,315);
            allBlocks[i] = demoGround;
        }
        
        Label frame = new Label(new GreenfootImage("frame-1.png"));
        addObject(frame,711/2,400/2);
        
        GreenfootImage backButton = new GreenfootImage("orangeButton.png");
        backButton.setFont(constantia);
        backButton.drawString("BACK",45,30);
        backButton.scale(100,18);
        BackButton back = new BackButton(new StartingScreen(),backButton);
        addObject(back,70,22);
        
        desc = new Label("The default controls for movement are:\n" + 
                          "'up arrow' — jump\n" + 
                          "'left arrow' — move left\n" + 
                          "'right arrow' — move right\n" + 
                          "It is also possible to jump in midair once\n" + 
                          "for each jump that starts on the ground\n" + 
                          "The controls can be changed in the settings,\n" + 
                          "which can be accessed in the home screen\n" + 
                          "Feel free to use the zone on the right to test\n" + 
                          "movement",27);
        addObject(desc,250,220);
    }

    public void act(){
        for(Block block : allBlocks){
            if(block.getX() < 415){
                block.setLocation(block.getX()+4*65,block.getY());
            }else if(block.getX() > 715){
                block.setLocation(block.getX()-4*65,block.getY());
            }
        }
    }
}
