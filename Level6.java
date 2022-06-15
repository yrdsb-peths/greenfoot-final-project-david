import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level6 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level6 extends Levels
{
    private GreenfootImage grass = new GreenfootImage("grass block.png");
    private GreenfootImage dirt = new GreenfootImage("dirt.png");
    private GreenfootImage dirt1 = new GreenfootImage(48,48*43);
    private GreenfootImage grassStripImage = new GreenfootImage(48,48*43);
    private Decor[] skyScroll = new Decor[2];
    private Decor[] darknessScroll = new Decor[2];
    private GreenfootImage stoneStrip = new GreenfootImage(48,48*43);
    private GreenfootImage stone = new GreenfootImage("stone0.png");
    private GreenfootImage sky = new GreenfootImage("sky.png");
    private GreenfootImage darkness = new GreenfootImage(716,48*43);
    private GreenfootImage magmaBlock = new GreenfootImage("magma block.png");
    private GreenfootImage ice = new GreenfootImage("ice.png");
    private GreenfootImage mossStone = new GreenfootImage("moss stone.png");
    public Player player;
    private InGameText title;
    /**
     * Constructor for objects of class Level6.
     * 
     */
    public Level6()
    {
        super(711,400,1,false,5);
        construct();
        player = new Player();
        addCameraFollower(player, 0, 75);
        addCameraFollower(pause,-320,-170);
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
        for(Decor darkness: darknessScroll){
            if(darkness.getX() < -716/2){
                darkness.setLocation(darkness.getX()+716*2,darkness.getY());
            }
            if(darkness.getX() > 716*3/2){
                darkness.setLocation(darkness.getX()-716*2,darkness.getY());
            }
        }
    }

    public void construct(){
        GreenfootImage arrowSign = new GreenfootImage("arrow sign.png");
        arrowSign.scale(40,60);
        Decor sign = new Decor(arrowSign);
        
        addObject(sign,48*4,287);
        sky.scale(716,400);
        darkness.setColor(Color.BLACK);
        darkness.fill();
        for(int i = 0; i < 2; i++){
            Decor darkness = new Decor(this.darkness);
            darknessScroll[i] = darkness;
            addObject(darkness,-716/2+716*i,getHeight()/2+48*25);
            Decor sky = new Decor(this.sky);
            skyScroll[i] = sky;
            addObject(sky,-716/2+716*i,-getHeight()/2);
        }
        stone.scale(48,48);
        createSpawnPlatform(stone,stoneStrip,true);
        for(int i = 0; i < 16; i++){
            addStoneStrip(48*i+48*4,287);
        }
        GreenfootImage gate = new GreenfootImage("gate6.png");
        gate.mirrorHorizontally();
        addObject(new Decor(gate),350,189);
        magmaBlock.scale(48,48);
        ice.scale(48,48);
        mossStone.scale(48,48);
        dirt.scale(48,48);
        grass.scale(48,48);
        createSpawnPlatform(dirt,grassStripImage,true);
        addObject(new Block(magmaBlock),100,300);
        addObject(new Block(magmaBlock),-50,325);
        addObject(new Block(magmaBlock),-200,325);
        addObject(new Block(magmaBlock),-350,300);
        addObject(new Block(magmaBlock),-450,300);
        addObject(new Checkpoint(),-450,249);
        addHeadHitter(-500,280);
        addHeadHitter(-600,280);
        addHeadHitter(-700,280);
        addHeadHitter(-800,280);
        addHeadHitter(-900,280);
        addObject(new IceBlock(ice),-1000,280);
        addObject(new Checkpoint(),-1000,229);
        addMoss(-1048,280);
        for(int i = 0; i < 3; i++){
            addWall(mossStone,-1248-i*400,60);
            addSlime(-1248-i*400,380);
            addMoss(-1448-i*400,280);
        }
        addObject(new Checkpoint(),-2248,229);
        GreenfootImage cobble = new GreenfootImage("cobblestone.png");
        cobble.scale(48,48);
        setupBreakingBlocks(cobble,2);
        addBreakingBlock(breakingBlock[0],trackers[0],-2400,300);
        addBreakingBlock(breakingBlock[1],trackers[1],-2600,250);
        addBreakingBlock(breakingBlock[2],trackers[2],-2900,300);
        addBreakingBlock(breakingBlock[3],trackers[3],-3100,280);
        addBreakingBlock(breakingBlock[4],trackers[4],-3450,300);
        addObject(new Block(stone),-3700,300);
        addObject(new Checkpoint(),-3700,249);
        addGrassStrip(-3850,300);
        addGrassStrip(-3898,300);
        addGrassStrip(-4048,320);
        addGrassStrip(-4096,320);
        addGrassStrip(-4246,280);
        addGrassStrip(-4294,280);
        for(int i = 0; i < 16;i++){
            addGrassStrip(-4442-48*i,280);
        }
        addHouse();
        addObject(new Block(new GreenfootImage(50,1000)),-4800,250);
        title = new InGameText("HOME SWEET HOME",Color.WHITE,new Font("Constantia",true,false,40),true);
        addObject(title,535,80);
    }

    public void addStoneStrip(int x, int y){
        Block stoneStrip = new Block(this.stoneStrip);
        addObject(stoneStrip,x,y+22*48);
    }

    public void addHeadHitter(int x, int y){
        IceBlock iceBlock1 = new IceBlock(ice);
        addObject(iceBlock1,x,y);
        addWall(ice,x-50,y-240);
    }

    public void addWall(GreenfootImage image,int x,int y){
        GreenfootImage wallImage = new GreenfootImage(48,48*4);
        for(int i = 0; i < 4; i++){
            wallImage.drawImage(image,0,48*i);
        }
        Block wall = new Block(wallImage);
        addObject(wall, x,y);
    }

    public void addMoss(int x,int y){
        Block moss = new Block(mossStone);
        addObject(moss,x,y);
    }

    public void addSlime(int x,int y){
        SlimeBlock slime = new SlimeBlock();
        addObject(slime,x,y);
        if(y >= 350){
            GreenfootImage arrowImage = new GreenfootImage("arrow.png");
            arrowImage.scale(75,30);
            Decor arrow = new Decor(arrowImage);
            addObject(arrow,x,250);
            arrow.setRotation(270);
        }
    }
    
    public void addGrassStrip(int x,int y){
        grassStripImage.drawImage(grass,0,0);
        Block grassStrip = new Block(grassStripImage);
        addObject(grassStrip,x,y+48*22);
    }
    
     public void addHouse(){
        GreenfootImage houseImage = new GreenfootImage("house.png");
        houseImage.scale(87*7,28*7);
        Gate house = new Gate(houseImage,true);
        addObject(house,-4720,206);
    }
}
