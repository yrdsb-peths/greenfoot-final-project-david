import greenfoot.*;

public class World1 extends ScrollWorld
{
    /**
     * Constructor for objects of class DemoWorld.
     */
    public World1()
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
        Block block = new Block(false);
        addObject(block,355,320);
        Block block1 = new Block(false);
        addObject(block1, 555, 320);
        Block block2 = new Block(false);
        addObject(block2, 855, 400);
        BlockBuilder bob = new BlockBuilder();
        addObject(bob, -100, -100);
    }
}