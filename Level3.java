import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level3 extends Levels
{
    public Player player;
    private GreenfootImage mossStrip = new GreenfootImage(48,48*43);
    private GreenfootImage ground = new GreenfootImage(716,48*43);
    private GreenfootImage sky = new GreenfootImage(716,400);
    private GreenfootImage mossBlock = new GreenfootImage("moss.png");
    private Decor[] groundScroll = new Decor[2];
    private Decor[] skyScroll = new Decor[2];
    private InGameText title;
    private GreenfootImage mossStone = new GreenfootImage("moss stone.png");
    /**
     * Constructor for objects of class Level4.
     * 
     */

    public Level3()
    {
        super(711,400,1,false,0);
        construct();
        Player.speed = 4;
        player = new Player();
        addCameraFollower(player,0,75);
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
        for(Decor ground: groundScroll){
            if(ground.getX() < -716/2){
                ground.setLocation(ground.getX()+716*2,ground.getY());
            }
            if(ground.getX() > 716*3/2){
                ground.setLocation(ground.getX()-716*2,ground.getY());
            }
        }
        clickedPauseButton();
    }

    public void construct(){
        ground.setColor(Color.BLACK);
        ground.fill();
        for(int i = 0; i < 2; i++){
            Decor ground = new Decor(this.ground);
            groundScroll[i] = ground;
            addObject(ground,-716/2+716*i,getHeight()/2+48*25);
        }
        GreenfootImage sign = new GreenfootImage("arrow sign.png");
        sign.mirrorHorizontally();
        sign.scale(40,60);
        Sign warning = new Sign(sign,"                     NOTICE: \n            STICKY & BOUNCY \n           SUBSTANCE AHEAD \n  (Tip: Don't jump when you land)");
        addObject(warning,540,300);
        mossBlock.scale(48,48);
        mossStone.scale(48,48);
        createSpawnPlatform(mossBlock,mossStrip,false);
        for(int i = 0; i < 16;i++){
            addMossStrip(-48*4+48*i,287);
        }
        addObject(new Decor(new GreenfootImage("gate3.png")),350,187);

        sky.setColor(new Color(44,48,76));
        sky.fill();
        for(int i = 0; i < 2; i++){
            Decor sky1 = new Decor(sky);
            skyScroll[i] = sky1;
            addObject(sky1,-716/2+716*i,-getHeight()/2);
        }

        addSlime(700,400);
        addMoss(1000,300);
        for(int i = 0; i < 5; i++){
            addSlime(1300+i*300,405);
        }
        addMoss(2700,300);
        addObject(new Checkpoint(),2700,249);
        addWall(2900,80);
        addSlime(2900,400);
        addMoss(3100,300);
        addWall(3300,80);
        addSlime(3300,400);
        addMoss(3500,300);
        addWall(3700,80);
        addSlime(3700,400);
        for(int i = 0; i < 16; i++){
            addMossStrip(3900+i*48,252);
        }
        addObject(new Block(new GreenfootImage(50,1000)),4325,300);
        addObject(new Gate(new GreenfootImage("gate5.png")),4250,167);
        title = new InGameText("FUNGAL FOREST",Color.WHITE,new Font("Constantia",true,false,40),true);
        addObject(title,535,80);
    }

    public void addMoss(int x,int y){
        Block moss = new Block(mossStone);
        addObject(moss,x,y);
    }

    public void addMossStrip(int x, int y){
        Block mossBlock = new Block(mossStrip);
        addObject(mossBlock,x,y+48*22);
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
    
    public void addWall(int x,int y){
        GreenfootImage wallImage = new GreenfootImage(48,48*4);
        for(int i = 0; i < 4; i++){
            wallImage.drawImage(mossStone,0,48*i);
        }
        Block wall = new Block(wallImage);
        addObject(wall, x,y);
    }
}