import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The sixth and last level of the game
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Level6 extends Levels
{
    private GreenfootImage grassStripImage = new GreenfootImage(48,2064);
    private GreenfootImage stoneStrip = new GreenfootImage(48,2064);
    private InGameText title;
    /**
     * Constructs level 6
     */
    public Level6()
    {
        super(711,400,1,false,5);
        
        addMisc();
        construct();
        
        addCameraFollower(player,0,75);
        addCameraFollower(pause,-320,-170);
        addObject(title,535,80);
        
        addCameraFollower(cheatButton,getWidth()/2-5,getHeight()/2-5);
    }

    public void act(){
        scrollSkyAndVoid();
        toggleCheats();
    }

    public void addMisc(){
        addSky(new Color(168,214,255));
        addVoid(Color.BLACK);
        
        title = new InGameText("HOME SWEET HOME",Color.WHITE,DetailsRenderer.constantiaB40,true);
        
        Decor sign = new Decor(DetailsRenderer.arrowSign);
        addObject(sign,192,287);
        
        addObject(new Checkpoint(),-450,249);
        addObject(new Checkpoint(),-1000,229);
        addObject(new Checkpoint(),-2248,229);
        addObject(new Checkpoint(),-3700,249);
        
        GreenfootImage gate = new GreenfootImage("gate6.png");
        gate.mirrorHorizontally();
        addObject(new Decor(gate),350,189);
        
        addObject(new Gate(new GreenfootImage("house.png"),true),-4720,206);
    }
    
    public void construct(){
        createSpawnPlatform(DetailsRenderer.stones[0],stoneStrip,true);
        createSpawnPlatform(DetailsRenderer.dirtBlock,grassStripImage,true);
        for(int i = 0; i < 16; i++){
            addStoneStrip(48*i+192,287);
        }
        for(int i = 0; i < 16;i++){
            addGrassStrip(-4442-48*i,280);
        }
        for(int i = 0; i < 3; i++){
            addWall(DetailsRenderer.mossStoneBlock,-1248-i*400,60);
            addSlime(-1248-i*400,380);
            addMoss(-1448-i*400,280);
        }
        addObject(new Block(DetailsRenderer.magmaBlock),100,300);
        addObject(new Block(DetailsRenderer.magmaBlock),-50,325);
        addObject(new Block(DetailsRenderer.magmaBlock),-200,325);
        addObject(new Block(DetailsRenderer.magmaBlock),-350,300);
        addObject(new Block(DetailsRenderer.magmaBlock),-450,300);
        
        addHeadHitter(-500,280);
        addHeadHitter(-600,280);
        addHeadHitter(-700,280);
        addHeadHitter(-800,280);
        addHeadHitter(-900,280);
        
        addObject(new IceBlock(DetailsRenderer.iceBlock),-1000,280);
        addMoss(-1048,280);
        
        setupBreakingBlocks(DetailsRenderer.cobblestoneBlock,2);
        addBreakingBlock(breakingBlock[0],trackers[0],-2400,300);
        addBreakingBlock(breakingBlock[1],trackers[1],-2600,250);
        addBreakingBlock(breakingBlock[2],trackers[2],-2900,300);
        addBreakingBlock(breakingBlock[3],trackers[3],-3100,280);
        addBreakingBlock(breakingBlock[4],trackers[4],-3450,300);
        
        addObject(new Block(DetailsRenderer.stones[0]),-3700,300);
        
        addGrassStrip(-3850,300);
        addGrassStrip(-3898,300);
        addGrassStrip(-4048,320);
        addGrassStrip(-4096,320);
        addGrassStrip(-4246,280);
        addGrassStrip(-4294,280);
        
        addObject(new Block(DetailsRenderer.barrier),-4800,250);
        topBorder = new Block(new GreenfootImage(5386,50));
        addObject(topBorder,-2113,-125);
    }

    /**
     * Adds a strip of stone to the world
     * 
     * @param x The x coordinate to add the strip at
     * @param y The y coordinate to add the strip at
     */
    public void addStoneStrip(int x, int y){
        Block stoneStrip = new Block(this.stoneStrip);
        addObject(stoneStrip,x,y+1056);
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
        addWall(DetailsRenderer.iceBlock,x-50,y-240);
    }

    /**
     * Adds a wall
     * 
     * @param x The x coordinate to add the wall at
     * @param y The y coordinate to add the wall at
     */
    public void addWall(GreenfootImage image,int x,int y){
        GreenfootImage wallImage = new GreenfootImage(48,192);
        for(int i = 0; i < 4; i++){
            wallImage.drawImage(image,0,48*i);
        }
        Block wall = new Block(wallImage);
        addObject(wall, x,y);
    }

    /**
     * Adds a moss block
     * 
     * @param x The x coordinate to add the moss block at
     * @param y The y coordinate to add the moss block at
     */
    public void addMoss(int x,int y){
        Block moss = new Block(DetailsRenderer.mossStoneBlock);
        addObject(moss,x,y);
    }

    /**
     * Adds a slime block
     * 
     * @param x The x coordinate to add the slime block at
     * @param y The y coordinate to add the slime block at
     */
    public void addSlime(int x,int y){
        SlimeBlock slime = new SlimeBlock();
        addObject(slime,x,y);
        if(y >= 350){
            Decor arrow = new Decor(DetailsRenderer.redArrow);
            addObject(arrow,x,250);
            arrow.setRotation(270);
        }
    }
    
    /**
     * Adds a long strip of dirt with grass at the top
     * 
     * @param x The x coordinate to add the strip at
     * @param y The y coordinate to add the strip at
     */
    public void addGrassStrip(int x,int y){
        grassStripImage.drawImage(DetailsRenderer.grassBlock,0,0);
        Block grassStrip = new Block(grassStripImage);
        addObject(grassStrip,x,y+1056);
    }
}
