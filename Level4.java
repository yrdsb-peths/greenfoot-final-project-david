import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The fourth level of the game. This is an ice themed level.
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Level4 extends Levels
{
    private GreenfootImage iceStrip = new GreenfootImage(48,2064);
    private InGameText title;
    /**
     * Constructs level 4.
     */
    public Level4()
    {
        super(711,400,1,false,0);
        
        addMisc();
        addBlocks();
        
        addCameraFollower(player,0,75);
        addCameraFollower(pause,-320,-170);
        addObject(title,535,80);
    }

    public void act(){
        scrollSkyAndVoid();
    }

    /**
     * Adds miscellaneous actors to the world
     */
    public void addMisc(){
        addSky(new Color(100,114,143));
        addVoid(Color.BLACK);
        
        title = new InGameText("FROZEN TUNDRA",Color.RED,DetailsRenderer.constantiaB40,true);
        
        Sign warning = new Sign(DetailsRenderer.arrowSign,"                    WARNING: \n            SLIPPERY GROUND \n              (double jump and \n            dashing are disabled)");
        addObject(warning,540,300);
        
        addObject(new Checkpoint(),600+130*4,275);
        addObject(new Decor(new GreenfootImage("gate4.png")),350,196);
        addObject(new Gate(new GreenfootImage("gate2.png")),2602,269);
    }
    
    /**
     * Adds all actors of the Block.class or its subclasses
     */
    public void addBlocks(){
        createSpawnPlatform(DetailsRenderer.iceBlock,iceStrip,false);
        for(int i = 0; i < 16; i++){
            addIceStrip(-192+48*i,287);
        }
        for(int i = 0; i < 4; i++){
            addHeadHitter(600+130*i,325);
        }
        for(int i = 0; i < 16; i++){
            addIceStrip(2252+48*i,324);
        }
        addObject(new IceBlock(DetailsRenderer.iceBlock),1120,324);
        addObject(new IceBlock(DetailsRenderer.iceBlock),1168,324);
        addObject(new IceBlock(DetailsRenderer.iceBlock),1660,324);
        addObject(new Block(DetailsRenderer.barrier),2677,300);
        
        addMovingBlock(1264,324,300,3,true);
        addMovingBlock(1756,324,400,4,true);
    }

    /**
     * Adds a long strip of ice
     * 
     * @param x The x coordinate to add the ice strip at
     * @param y The y coordinate to add the ice strip at
     */
    public void addIceStrip(int x,int y){
        IceBlock iceStrip = new IceBlock(this.iceStrip);
        addObject(iceStrip,x,y+1056);
    }

    /**
     * Adds a set of jumps that requires the player to hit their head on a block
     * 
     * @param x The x coordinate to add the head hitter at
     * @param y The y coordinate to add the head hitter at
     */
    public void addHeadHitter(int x, int y){
        IceBlock iceBlock1 = new IceBlock(DetailsRenderer.iceBlock);
        addObject(iceBlock1,x,y);
        IceBlock iceBlock2 = new IceBlock(DetailsRenderer.iceBlock);
        addObject(iceBlock2,x+70,y-150);
    }

    /**
     * Adds a moving block
     * 
     * @param x The x coordinate to add the moving block at
     * @param y The y coordinate to add the moving block at
     * @param maxDist The maximum distance that the moving block is allowed to travel in the direction its facing
     * @param speed The movment speed of the moving block
     * @param moveX Whether the block moves in the x direction (faces east if true, faces north if false)
     */
    public void addMovingBlock(int x,int y,int maxDist,int speed,boolean moveX){
        Decor tracker = new Decor(new GreenfootImage(10,10));
        MovingBlock movingBlock = new MovingBlock(DetailsRenderer.iceBlock,maxDist,tracker,speed,moveX);
        addObject(movingBlock,x,y);
        addObject(tracker,x,y);
        if(!moveX){
            movingBlock.setRotation(270);
        }
    }
}
