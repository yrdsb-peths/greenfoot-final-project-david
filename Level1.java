import greenfoot.*;

/**
 * The first level of the game. Is a forest themed level.
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Level1 extends Levels
{   
    private GreenfootImage dirt = new GreenfootImage(48,2064);
    private InGameText title;

    /**
     * Constructs Level1
     */
    public Level1()
    {
        super(711, 400, 1,false,0);
        
        addMisc();
        addBlocks();
        
        addCameraFollower(player,0,75);
        addCameraFollower(pause,-320,-170);
        addObject(title,520,80);
        
        addCameraFollower(cheatButton,getWidth()/2-5,getHeight()/2-5);
    }
    
    public void act(){
        scrollSkyAndVoid();
        toggleCheats();
    }

    /**
     * Adds miscellaneous actors to the level
     */
    public void addMisc(){
        addVoid(Color.BLACK);
        addSky(new Color(168,214,255));
        
        title = new InGameText("SETTING OFF",Color.WHITE,DetailsRenderer.constantiaB40,true);
        
        Decor sign = new Decor(DetailsRenderer.arrowSign);
        Decor sign1 = new Decor(DetailsRenderer.arrowSign);
        addObject(sign,530,300);
        addObject(sign1,1820,250);
        
        addObject(new Checkpoint(),1446,240);
        addObject(new Decor(new GreenfootImage("house.png")),100,223);
        addObject(new Gate(new GreenfootImage("gate1.png")),2750,197);
    }
    
    /**
     * Adds all actors of the Block.class or its subclasses
     */
    private void addBlocks()
    {
        createSpawnPlatform(DetailsRenderer.dirtBlock,dirt,false);
        for(int i = 0; i < 16; i++){
            addGrass(-192+48*i,345);
        }
        for(int i = 0; i < 16; i++){
            addGrass(2400+48*i,300);
        }
        addGrass(700,345);
        addGrass(748,345);
        addGrass(796,345);
        addGrass(930,280);
        addGrass(978,280);
        addGrass(1026,280);
        addGrass(1150,200);
        addGrass(1198,200);
        addGrass(1246,200);
        addGrass(1350,290);
        addGrass(1398,290);
        addGrass(1446,290);
        addGrass(1716,290);
        addGrass(1764,290);
        addGrass(1812,290);
        addGrass(2212,290);
        addGrass(2260,290);
        
        addObject(new Block(DetailsRenderer.barrier),2825,225);
        topBorder = new Block(new GreenfootImage(2850,50));
        addObject(topBorder,1425,-125);
    }

    /**
     * Adds a long strip of dirt with grass at the top
     * 
     * @param x The x coordinate to add the strip at
     * @param y The y coordinate to add the strip at
     */
    private void addGrass(int x, int y){
        Block grassBlock = new Block(DetailsRenderer.grassBlock);
        addObject(grassBlock,x,y);
        Block dirtBlock = new Block(dirt);
        addObject(dirtBlock,x,y+1056);
    }
}