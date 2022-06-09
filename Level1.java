import greenfoot.*;

public class Level1 extends Levels
{   
    private GreenfootImage grass = new GreenfootImage("grass block.png");
    private GreenfootImage dirt1 = new GreenfootImage(48,48*43);
    private Decor[] skyScroll = new Decor[2];
    private Decor[] darknessScroll = new Decor[2];
    public Decor exit;
    public Player player;
    InGameText title;

    /**
     * Constructor for objects of class DemoWorld.
     */
    public Level1()
    {
        super(711, 400, 1,false);
        prepare();
        player = new Player();
        addCameraFollower(player, 0, 75);
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        GreenfootImage dirt = new GreenfootImage("dirt.png");
        addDarkness();
        addSky();
        grass.scale(48,48);
        dirt.scale(48,48);
        createSpawnPlatform(dirt,dirt1);
        
        GreenfootImage sign = new GreenfootImage("arrow sign.png");
        sign.mirrorHorizontally();
        sign.scale(40,60);
        Decor arrow = new Decor(sign);
        addObject(arrow,530,300);
        Decor arrow1 = new Decor(sign);
        addObject(arrow1,1820,250);

        for(int i = 0; i < 16; i++){
            addGrass(-48*4+48*i,345);
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
        addObject(new Checkpoint(),1446,240);

        addGrass(1716,290);
        addGrass(1764,290);
        addGrass(1812,290);

        addGrass(2212,290);
        addGrass(2260,290);

        for(int i = 0; i < 16; i++){
            addGrass(2400+48*i,300);
        }

        addObject(new Gate(new GreenfootImage("gate.png")),2750,197);

        addHouse();

        addCameraFollower(pause,-320,-170);
        
        title = new InGameText("SETTING OFF",Color.WHITE,new Font("Constantia",true,false,40),true);
        addObject(title,520,80);
        
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
        for(Decor black: darknessScroll){
            if(black.getX() < -716/2){
                black.setLocation(black.getX()+716*2,black.getY());
            }
            if(black.getX() > 716*3/2){
                black.setLocation(black.getX()-716*2,black.getY());
            }
        }

        clickedPauseButton();
    }

    private void addGrass(int x, int y){
        Block grassBlock = new Block(false,grass);
        addObject(grassBlock,x,y);
        Block dirtBlock = new Block(false,dirt1);
        addObject(dirtBlock,x,y+48*22);
    }

    public void addSky(){
        GreenfootImage sky = new GreenfootImage("sky.png");
        sky.scale(716,400);
        for(int i = 0; i < 2; i++){
            Decor sky1 = new Decor(sky);
            skyScroll[i] = sky1;
            addObject(sky1,-716/2+716*i,-getHeight()/2);
        }
    }

    public void addDarkness(){
        GreenfootImage darkness = new GreenfootImage(716,48*43);
        darkness.setColor(Color.BLACK);
        darkness.fill();
        for(int i = 0; i < 2; i++){
            Decor black = new Decor(darkness);
            darknessScroll[i] = black;
            addObject(black,-716/2+716*i,getHeight()/2+48*25);
        }
    }

    public void addHouse(){
        GreenfootImage houseImage = new GreenfootImage("house.png");
        houseImage.scale(87*7,28*7);
        Decor house = new Decor(houseImage);
        addObject(house,100,223);
    }
}