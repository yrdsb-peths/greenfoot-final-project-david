import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The second level of the game. Is a cave themed level.
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Level2 extends Levels
{
    private GreenfootImage stone = new GreenfootImage(48,2064);
    private InGameText title;
    /**
     * Constructs Level2.
     */
    public Level2()
    {
        super(711,400,1,false,14);
        
        addMisc();
        addBlocks();
        
        addCameraFollower(player,0,75);
        addCameraFollower(pause,-320,-170);
        addObject(title,550,80);
        
        addCameraFollower(cheatButton,getWidth()/2-5,getHeight()/2-5);
    }

    public void act(){
        scrollSkyAndVoid();
        resetBreakingBlocks();
        toggleCheats();
    }

    /**
     * Adds miscellaneous actors to this world
     */
    public void addMisc(){
        addSky(Color.BLACK);
        addVoid(Color.BLACK);
        
        title = new InGameText("CRUMBLING CAVE",Color.WHITE,DetailsRenderer.constantiaB40,true);
        
        Sign arrowSign = new Sign(DetailsRenderer.arrowSign,"                    WARNING! \n           UNSTABLE TERRAIN \n        PROCEED WITH HASTE");
        addObject(arrowSign,540,300);
        
        Decor arrow = new Decor(DetailsRenderer.redArrow);
        addObject(arrow,2300,175);
        arrow.setRotation(270);
        
        addObject(new Checkpoint(),1200,300);
        addObject(new Decor(new GreenfootImage("gate1.png")),getWidth()/2-5,233);
        addObject(new Gate(new GreenfootImage("gate3.png")),3750,200);
    }
    
    /**
     * Adds all actors of the Block.class or its subclasses
     */
    public void addBlocks(){
        createSpawnPlatform(DetailsRenderer.stones[0],stone,false);
        for(int i = 0; i < 16; i++){
            addStone(-192+48*i,287);
        }
        for(int i = 0; i < 16; i++){
            addStone(3400+48*i,300);
        }
        setupBreakingBlocks(DetailsRenderer.cobblestoneBlock,2);
        
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
        
        addStone(1200,300);
        
        addObject(new Block(DetailsRenderer.barrier),3825,225);
        topBorder = new Block(new GreenfootImage(3850,50));
        addObject(topBorder,1925,-125);
    }

    /**
     * Adds a strip of stone
     * 
     * @param x The x coordinate to add the strip at
     * @param y The y coordinate to add the strip at
     */
    public void addStone(int x, int y){
        Block stoneBlock = new Block(stone);
        addObject(stoneBlock,x,y+1056);
    }
}
