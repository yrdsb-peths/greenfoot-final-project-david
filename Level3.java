import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The third level of the game. Is a fungus themed level.
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Level3 extends Levels
{
    private GreenfootImage mossStrip = new GreenfootImage(48,2064);
    private InGameText title;
    /**
     * Constructs level 3
     */
    public Level3()
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
        addSky(new Color(44,48,76));
        addVoid(Color.BLACK);
        
        title = new InGameText("FUNGAL FOREST",Color.WHITE,new Font("Constantia",true,false,40),true);
        
        Sign warning = new Sign(DetailsRenderer.arrowSign,"                     NOTICE: \n            STICKY & BOUNCY \n           SUBSTANCE AHEAD \n  (Tip: Don't jump when you land)");
        addObject(warning,540,300);
        
        addObject(new Checkpoint(),2700,249);
        addObject(new Decor(new GreenfootImage("gate3.png")),350,187);
        addObject(new Gate(new GreenfootImage("gate5.png")),4250,167);
    }
    
    /**
     * Adds all actors of the Block.class or its subclasses
     */
    public void addBlocks(){
        createSpawnPlatform(DetailsRenderer.mossBlock,mossStrip,false);
        for(int i = 0; i < 16;i++){
            addMossStrip(-192+48*i,287);
        }
        for(int i = 0; i < 5; i++){
            addSlime(1300+i*300,405);
        }
        for(int i = 0; i < 16; i++){
            addMossStrip(3900+i*48,252);
        }
        addSlime(700,400);
        addSlime(2900,400);
        addSlime(3300,400);
        addSlime(3700,400);
        
        addMoss(1000,300);
        addMoss(2700,300);
        addMoss(3100,300);
        addMoss(3500,300);
        
        addWall(2900,80);
        addWall(3300,80);
        addWall(3700,80);
        
        addObject(new Block(DetailsRenderer.barrier),4325,300);
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
     * Adds a strip of moss
     * 
     * @param x The x coordinate to add the moss strip at
     * @param y The y coordinate to add the moss strip at
     */
    public void addMossStrip(int x, int y){
        Block mossBlock = new Block(mossStrip);
        addObject(mossBlock,x,y+1056);
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
     * Adds a wall
     * 
     * @param x The x coordinate to add the wall at
     * @param y The y coordinate to add the wall at
     */
    public void addWall(int x,int y){
        GreenfootImage wallImage = new GreenfootImage(48,192);
        for(int i = 0; i < 4; i++){
            wallImage.drawImage(DetailsRenderer.mossStoneBlock,0,48*i);
        }
        Block wall = new Block(wallImage);
        addObject(wall, x,y);
    }
}