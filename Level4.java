import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LEvel5 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level4 extends Levels
{
    public Player player;
    private GreenfootImage iceStrip = new GreenfootImage(48,48*43);
    private GreenfootImage ground = new GreenfootImage(716,48*43);
    private GreenfootImage sky = new GreenfootImage(716,400);
    private GreenfootImage ice = new GreenfootImage("ice.png");
    private Decor[] groundScroll = new Decor[2];
    private Decor[] skyScroll = new Decor[2];
    private InGameText title;
    private MovingBlock[] movingBlocks = new MovingBlock[2];
    private int movingBlockCount = 0;
    /**
     * Constructor for objects of class Level5.
     * 
     */
    public Level4()
    {
        super(711,400,1,false,0);
        player = new Player();
        construct();
        addCameraFollower(pause,-320,-170);
        addCameraFollower(player,0,75);
    }

    public void act(){
        for(Decor sky: skyScroll){
            if(sky.getX() < -716/2){
                sky.setLocation(sky.getX()+716*2,sky.getY());
            }
            if(sky.getX() > 716*3/2){
                sky.setLocation(sky.getX()-716*2,sky.getY());
            }
        }
        for(Decor ground: groundScroll){
            if(ground.getX() < -716/2){
                ground.setLocation(ground.getX()+716*2,ground.getY());
            }
            if(ground.getX() > 716*3/2){
                ground.setLocation(ground.getX()-716*2,ground.getY());
            }
        }
        for(MovingBlock block:movingBlocks){
            if(block.moveX){
                if(block.tracker.getX() == block.getX()){
                    block.dir = 1;
                }else if(block.getX() == block.tracker.getX() + block.maxDist){
                    block.dir = -1;
                }
            }else{
                if(block.tracker.getY() == block.getY()){
                    block.dir = 1;
                }else if(block.getY() == block.tracker.getY() - block.maxDist){
                    block.dir = -1;
                }
            }
            block.move(block.dir*block.speed);
        }
        clickedPauseButton();
        Player.canJump = false;
    }

    public void construct(){
        GreenfootImage sign = new GreenfootImage("arrow sign.png");
        sign.mirrorHorizontally();
        sign.scale(40,60);
        Sign warning = new Sign(sign,"                    WARNING: \n            SLIPPERY GROUND \n              (double jump and \n            dashing are disabled)");
        addObject(warning,540,300);
        ground.setColor(Color.BLACK);
        ground.fill();
        for(int i = 0; i < 2; i++){
            Decor darkness = new Decor(ground);
            groundScroll[i] = darkness;
            addObject(darkness,-716/2+716*i,getHeight()/2+48*25);
        }
        ice.scale(48,48);
        addObject(new Decor(new GreenfootImage("gate4.png")),350,196);
        createSpawnPlatform(ice,iceStrip,false);
        for(int i = 0; i < 16; i++){
            addIceStrip(-48*4+48*i,287);
        }
        sky.setColor(new Color(100,114,143));
        sky.fill();
        for(int i = 0; i < 2; i++){
            Decor sky = new Decor(this.sky);
            skyScroll[i] = sky;
            addObject(sky,-716/2+716*i,-getHeight()/2);
        }
        for(int i = 0; i < 4; i++){
            addHeadHitter(600+130*i,325);
        }
        addObject(new IceBlock(ice),600+130*4,324);
        addObject(new IceBlock(ice),600+130*4+48,324);
        addObject(new Checkpoint(),600+130*4,275);
        addMovingBlock(1216+48,324,300,3,true);
        addObject(new IceBlock(ice),1612+48,324);
        addMovingBlock(1756,324,400,4,true);
        for(int i = 0; i < 16; i++){
            addIceStrip(2252+48*i,324);
        }
        addObject(new Block(new GreenfootImage(50,1000)),2677,300);
        addObject(new Gate(new GreenfootImage("gate2.png")),2602,269);
        title = new InGameText("FROZEN TUNDRA",Color.RED,new Font("Constantia",true,false,40),true);
        addObject(title,535,80);
    }

    public void addIceStrip(int x,int y){
        IceBlock iceStrip = new IceBlock(this.iceStrip);
        addObject(iceStrip,x,y+48*22);
    }

    public void addHeadHitter(int x, int y){
        IceBlock iceBlock1 = new IceBlock(ice);
        addObject(iceBlock1,x,y);
        IceBlock iceBlock2 = new IceBlock(ice);
        addObject(iceBlock2,x+70,y-150);
    }

    public void addMovingBlock(int x,int y,int maxDist,int speed,boolean moveX){
        Decor tracker = new Decor(new GreenfootImage(10,10));
        MovingBlock movingBlock = new MovingBlock(ice,maxDist,tracker,speed,moveX);
        addObject(movingBlock,x,y);
        addObject(tracker,x,y);
        movingBlocks[movingBlockCount] = movingBlock;
        movingBlockCount++;
        if(!moveX){
            movingBlock.setRotation(270);
        }
    }
}
