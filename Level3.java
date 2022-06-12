import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level3 extends Levels
{
    public Player player;
    private GreenfootImage stone1 = new GreenfootImage(48,48*43);
    private GreenfootImage[] stoneFade = new GreenfootImage[8];
    private GreenfootImage lava = new GreenfootImage(716,48*43);
    private GreenfootImage sky = new GreenfootImage(716,400);
    private GreenfootImage magmaBlock = new GreenfootImage("magma block.png");
    private Decor[] lavaScroll = new Decor[2];
    private Decor[] skyScroll = new Decor[2];
    private BreakingBlock[] breakingBlock = new BreakingBlock[8];
    private LocationTracker[] trackers = new LocationTracker[8];
    private InGameText title;
    /**
     * Constructor for objects of class Level3.
     * 
     */
    public Level3()
    {
        super(711,400,1,false);
        magmaBlock.scale(48,48);
        construct();
        Player.g = 1.1;
        player = new Player();
        addCameraFollower(player,0,75);
        addCameraFollower(pause,-320,-170);
    }

    public void act(){
        clickedPauseButton();
        for(Decor sky: skyScroll){
            if(sky.getX() < -716/2){
                sky.setLocation(sky.getX()+716*2,sky.getY());
            }
            if(sky.getX() > 716*3/2){
                sky.setLocation(sky.getX()-716*2,sky.getY());
            }
        }
        for(Decor yellow: lavaScroll){
            if(yellow.getX() < -716/2){
                yellow.setLocation(yellow.getX()+716*2,yellow.getY());
            }
            if(yellow.getX() > 716*3/2){
                yellow.setLocation(yellow.getX()-716*2,yellow.getY());
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
            for(int i = 0; i < 8; i++){
                try{
                    breakingBlock[i].x = trackers[i].getX()-(player.getCheckpoint().getX()-getWidth()/2);
                    breakingBlock[i].y = trackers[i].getY();
                }catch(IllegalStateException e){

                }
            }
        }
    }

    public void construct(){
        GreenfootImage sign = new GreenfootImage("arrow sign.png");
        sign.mirrorHorizontally();
        sign.scale(40,60);
        Sign warning = new Sign(sign,"                    CAUTION! \n            INTENSE PRESSURE \n           STRONGER GRAVITY");
        addObject(warning,540,300);
        Sign warning1 = new Sign(sign,"                       Notice: \n              Long Path Ahead.");
        addObject(warning1,1098,325);
        
        lava.setColor(new Color(255,220,98));
        lava.fill();
        sky.setColor(new Color(16,4,120));
        sky.fill();
        for(int i = 0; i < 2; i++){
            Decor sky1 = new Decor(sky);
            skyScroll[i] = sky1;
            addObject(sky1,-716/2+716*i,-getHeight()/2);
        }
        for(int i = 0; i < 2; i++){
            Decor yellow = new Decor(lava);
            lavaScroll[i] = yellow;
            addObject(yellow,-716/2+716*i,getHeight()/2+48*25);
        }

        for(int i = 0; i < 8; i++){
            stoneFade[i] = new GreenfootImage("stone" + i + ".png");
            stoneFade[i].scale(48,48);
        }
        createSpawnPlatform(stone1);
        for(int i = 0; i < 16; i++){
            addStone(-48*4+48*i,287);
        }
        addObject(new Decor(new GreenfootImage("gate2.png")),getWidth()/2-5,233);

        GreenfootImage cobble = new GreenfootImage("cobblestone.png");
        cobble.scale(48,48);
        for(int i = 0; i < 8; i++){
            BreakingBlock cobblestone = new BreakingBlock(cobble,1);
            breakingBlock[i] = cobblestone;
            LocationTracker tracker = new LocationTracker();
            trackers[i] = tracker;
        }
        addBreakingBlock(breakingBlock[0],trackers[0],620,320);
        addBreakingBlock(breakingBlock[1],trackers[1],770,280);
        addObject(new Block(magmaBlock),1050,360);
        addObject(new Block(magmaBlock),1098,360);
        addObject(new Checkpoint(),1050,309);
        addBreakingBlock(breakingBlock[2],trackers[2],1200,280);
        addBreakingBlock(breakingBlock[3],trackers[3],1400,300);
        addBreakingBlock(breakingBlock[4],trackers[4],1550,240);
        addBreakingBlock(breakingBlock[5],trackers[5],1750,240);
        addBreakingBlock(breakingBlock[6],trackers[6],2000,300);
        addBreakingBlock(breakingBlock[7],trackers[7],2300,300);
        for(int i = 0; i < 16; i++){
            addStone(2500+48*i,300);
        }
        
        title = new InGameText("SCORCHED WASTELAND",Color.WHITE,new Font("Constantia",true,false,40),true);
        addObject(title,600,80);
        addObject(new Block(new GreenfootImage(50,1000)),2925,300);
        addObject(new Gate(new GreenfootImage("gate3.png")),2850,200);
    }

    public void createSpawnPlatform(GreenfootImage strip){
        for(int i = 0; i < 42; i++){
            if(i <= 7){
                strip.drawImage(stoneFade[i],0,i*48);
            }else{
                strip.drawImage(stoneFade[7],0,i*48);
            }
        }
        addObject(new Block(new GreenfootImage(50,10000)),125,225);
    }

    public void addStone(int x, int y){
        Block stoneBlock = new Block(stone1);
        addObject(stoneBlock,x,y+48*22);
    }
}
