import greenfoot.*;

public class Level1 extends ScrollWorld
{
    /**
     * Constructor for objects of class DemoWorld.
     */
    public Level1()
    {
        super(711, 400, 1,false);
        addCameraFollower(new Player(), 0, 0);
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        GreenfootImage grass = new GreenfootImage("grass block.png");
        grass.scale(72,72);
        GreenfootImage empty = new GreenfootImage(50,1000);
        Block barrier = new Block(false,empty);
        addObject(barrier,125,200);
        for(int i = 0; i < 10; i++){
            Block block = new Block(false,grass);
            addObject(block,-block.getImage().getWidth()*3+block.getImage().getWidth()*i,320);
        }
        BlockBuilder bob = new BlockBuilder();
        addObject(bob, -100, -100);
    }
}