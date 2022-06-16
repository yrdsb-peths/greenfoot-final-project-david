import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The fifth level of the game.
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Level5 extends Levels
{
    private GreenfootImage stone1 = new GreenfootImage(48,2064);
    private InGameText title;
    /**
     * Constructs level 5
     */
    public Level5()
    {
        super(711,400,1,false,8);
        
        addMisc();
        addBlocks();
        
        addCameraFollower(player,0,75);
        addCameraFollower(pause,-320,-170);
        addObject(title,600,80);
    }

    public void act(){
        scrollSkyAndVoid();
        resetBreakingBlocks();
    }

    /**
     * Adds miscellaneous actors to the world
     */
    public void addMisc(){
        addSky(new Color(16,4,120));
        addVoid(new Color(255,220,98));
        
        title = new InGameText("SCORCHED WASTELAND",Color.WHITE,DetailsRenderer.constantiaB40,true);
        
        Sign warning = new Sign(DetailsRenderer.arrowSign,"                    CAUTION! \n            INTENSE PRESSURE \n           STRONGER GRAVITY");
        addObject(warning,540,300);
        Sign warning1 = new Sign(DetailsRenderer.arrowSign,"                       Notice: \n              Long Path Ahead.");
        addObject(warning1,1098,325);
        
        addObject(new Checkpoint(),1050,309);
        addObject(new Decor(new GreenfootImage("gate2.png")),getWidth()/2-5,233);
        addObject(new Gate(new GreenfootImage("gate6.png")),2850,203);
    }
    
    /**
     * Adds all actors of the Block.class or its subclasses
     */
    public void addBlocks(){
        createSpawnPlatform(stone1);
        for(int i = 0; i < 16; i++){
            addStone(-192+48*i,287);
        }
        for(int i = 0; i < 16; i++){
            addStone(2500+48*i,300);
        }
        setupBreakingBlocks(DetailsRenderer.cobblestoneBlock,1);
        
        addBreakingBlock(breakingBlock[0],trackers[0],620,320);
        addBreakingBlock(breakingBlock[1],trackers[1],770,280);
        addBreakingBlock(breakingBlock[2],trackers[2],1200,280);
        addBreakingBlock(breakingBlock[3],trackers[3],1400,300);
        addBreakingBlock(breakingBlock[4],trackers[4],1550,240);
        addBreakingBlock(breakingBlock[5],trackers[5],1750,240);
        addBreakingBlock(breakingBlock[6],trackers[6],2000,300);
        addBreakingBlock(breakingBlock[7],trackers[7],2300,300);
        
        addObject(new Block(DetailsRenderer.magmaBlock),1050,360);
        addObject(new Block(DetailsRenderer.magmaBlock),1098,360);
        
        addObject(new Block(DetailsRenderer.barrier),2925,300);
    }

    /**
     * Overrides the superclass's createSpawnPlatform. Generates the strips of the spawn platform, fading to yellow
     * 
     * @param strip The image of the strip
     */
    public void createSpawnPlatform(GreenfootImage strip){
        for(int i = 0; i < 42; i++){
            if(i <= 7){
                strip.drawImage(DetailsRenderer.stones[i],0,i*48);
            }else{
                strip.drawImage(DetailsRenderer.stones[7],0,i*48);
            }
        }
        addObject(new Block(DetailsRenderer.barrier),125,225);
    }

    /**
     * Adds a strip of stone to the world
     * 
     * @param x The x coordinate to add the stone at
     * @param y The y coordinate to add the stone at
     */
    public void addStone(int x, int y){
        Block stoneBlock = new Block(stone1);
        addObject(stoneBlock,x,y+1056);
    }
}
