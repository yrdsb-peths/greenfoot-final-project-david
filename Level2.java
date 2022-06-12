import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level2 extends Levels
{
    private GreenfootImage stone1 = new GreenfootImage(48,48*43);
    private GreenfootImage stone = new GreenfootImage("stone0.png");
    private GreenfootImage darkness = new GreenfootImage(716,48*43);
    private Decor[] darknessScroll = new Decor[2];
    private Decor[] ceilingScroll = new Decor[2];
    public Player player;
    private BreakingBlock[] breakingBlock = new BreakingBlock[14];
    private LocationTracker[] trackers = new LocationTracker[14];
    private InGameText title;
    /**
     * Constructor for objects of class Level2.
     * 
     */

    public Level2()
    {
        super(711,400,1,false);
        stone.scale(48,48);
        darkness.setColor(Color.BLACK);
        darkness.fill();
        construct();
        player = new Player();
        addCameraFollower(player, 0, 75);
    }

    public void construct(){
        GreenfootImage sign = new GreenfootImage("arrow sign.png");
        sign.mirrorHorizontally();
        sign.scale(40,60);
        Sign arrowSign = new Sign(sign,"                    WARNING! \n           UNSTABLE TERRAIN \n        PROCEED WITH HASTE");
        addObject(arrowSign,540,300);
        for(int i = 0; i < 2; i++){
            Decor black = new Decor(darkness);
            darknessScroll[i] = black;
            addObject(black,-716/2+716*i,getHeight()/2+48*25-10);
        }
        createSpawnPlatform(stone,stone1);
        for(int i = 0; i < 16; i++){
            addStone(-48*4+48*i,287);
        }
        addObject(new Decor(new GreenfootImage("gate1.png")),getWidth()/2-5,233);
        for(int i = 0; i < 2; i++){
            GreenfootImage ceiling = new GreenfootImage(darkness);
            ceiling.scale(716,400);
            Decor ceiling1 = new Decor(ceiling);
            ceilingScroll[i] = ceiling1;
            addObject(ceiling1,-716/2+716*i,-getHeight()/2);
        }
        GreenfootImage cobble = new GreenfootImage("cobblestone.png");
        cobble.scale(48,48);
        for(int i = 0; i < 14; i++){
            BreakingBlock cobblestone = new BreakingBlock(cobble,2);
            breakingBlock[i] = cobblestone;
            LocationTracker tracker = new LocationTracker();
            trackers[i] = tracker;
        }
        addBreakingBlock(breakingBlock[0],trackers[0],600,300);
        addBreakingBlock(breakingBlock[1],trackers[1],755,350);
        addBreakingBlock(breakingBlock[2],trackers[2],850,350);
        addBreakingBlock(breakingBlock[3],trackers[3],1000,250);
        addBreakingBlock(breakingBlock[4],trackers[4],1450,350);
        addBreakingBlock(breakingBlock[5],trackers[5],1600,225);
        addBreakingBlock(breakingBlock[6],trackers[6],1800,225);
        addBreakingBlock(breakingBlock[7],trackers[7],2025,200);
        addBreakingBlock(breakingBlock[8],trackers[8],2300,375);
        addBreakingBlock(breakingBlock[9],trackers[9],2500,325);
        addBreakingBlock(breakingBlock[10],trackers[10],2725,300);
        addBreakingBlock(breakingBlock[11],trackers[11],2850,300);
        addBreakingBlock(breakingBlock[12],trackers[12],3100,250);
        addBreakingBlock(breakingBlock[13],trackers[13],3300,290);
        for(int i = 0; i < 16; i++){
            addStone(3400+48*i,300);
        }
        addObject(new Block(new GreenfootImage(50,1000)),3825,225);
        
        GreenfootImage redArrow = new GreenfootImage("arrow.png");
        redArrow.scale(75,30);
        Decor arrow = new Decor(redArrow);
        addObject(arrow,2300,175);
        arrow.setRotation(270);
        addStone(1200,300);
        addObject(new Checkpoint(),1200,300);
        addObject(new Gate(new GreenfootImage("gate2.png")),3750,243);
        
        title = new InGameText("CRUMBLING CAVE",Color.WHITE,new Font("Constantia",true,false,40),true);
        addObject(title,550,80);
        
        addCameraFollower(pause,-320,-170);
    }

    public void act(){
        for(Decor ceiling: ceilingScroll){
            if(ceiling.getX() < -716/2){
                ceiling.setLocation(ceiling.getX()+716*2,ceiling.getY());
            }
            if(ceiling.getX() > 716*3/2){
                ceiling.setLocation(ceiling.getX()-716*2,ceiling.getY());
            }
        }
        for(Decor black: darknessScroll){
            if(black.getX() < -716/2){
                black.setLocation(black.getX()+716*2,black.getY());
            }
            if(black.getX() > 716*3/2){
                black.setLocation(black.getX()-716*2,black.getY());
            }
        }
        for(BreakingBlock block:breakingBlock){
            if(block.removed && Player.warpedToCheckpoint){
                addObject(block,block.x,block.y);
                block.removed = false;
            }
            if(Player.warpedToCheckpoint){
                block.reset();
            }
        }
        if(player.getCheckpoint() != null){
            for(int i = 0; i < 12; i++){
                breakingBlock[i].x = trackers[i].getX()-(player.getCheckpoint().getX()-getWidth()/2);
                breakingBlock[i].y = trackers[i].getY();
            }
        }
        clickedPauseButton();
    }

    public void addStone(int x, int y){
        Block stoneBlock = new Block(stone1);
        addObject(stoneBlock,x,y+48*22);
    }
}
